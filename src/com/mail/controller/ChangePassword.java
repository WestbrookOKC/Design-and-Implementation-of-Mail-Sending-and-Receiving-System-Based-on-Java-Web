package com.mail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.modeler.MMailUser;

/**
 * 
 * 更改密码的servlet类
 */
public class ChangePassword extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession mySession = req.getSession(true);		//获取会话session
		
		mySession.setAttribute("errMsg", "");	//将页面打印的错误信息设为空
		
		String sUserName = (String)mySession.getAttribute("username");	//获取登录用户的用户名
		if(sUserName==null){											//如果登录用户名为空，跳转到登陆页面
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		if (!req.getParameterNames().hasMoreElements()){				//如果没有表单提交停留在该页面
	            mySession.setAttribute("curPage","changepassword");
	            resp.sendRedirect("/webmails/changePassword.jsp");
	            return;
	       }
		//如果页面上有数据提交（表单提交）执行以下代码
		String sOldPassword = req.getParameter("oldpassword").trim();	//获取页面输入的旧密码
		String sNewPassword = req.getParameter("newpassword").trim();	//获取用户输入的新密码
		if(sOldPassword!=null&&sOldPassword.length()>0){
			MMailUser mcp = new MMailUser();
			boolean b = mcp.changePassword(mySession, sUserName, sOldPassword, sNewPassword);//更改密码，返回true密码修改成功
			if(b){//如果更改成功
				mySession.invalidate();//将session初始化
				resp.sendRedirect("/webmails/changeSuccessful.jsp");//跳转到更改成功页面
			}else{//如果更改失败
				resp.sendRedirect("/webmails/changePassword.jsp");	//停留在本页面
			}
			
		}else{
			resp.sendRedirect("/webmails/index.jsp");//非法进入本页面时，跳转到登陆页面
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

