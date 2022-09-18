package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.bean.MailUser;
import com.mail.common.CommonInfo;
import com.mail.modeler.MMailUser;


public class CRegister extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession mySession = request.getSession(true);
        
        //清空错误消息
        mySession.setAttribute("errMsg","");
        
        //设置session中的页面值域
        mySession.setAttribute(CommonInfo.VIEWID_REGISTER, new Hashtable() );
        
        //是否进入默认页面
        if ( !request.getParameterNames().hasMoreElements() )
        {
            response.sendRedirect("/webmails/register.jsp");
            return;
        }
        
        //得到用户输入信息
        String sUsername = request.getParameter("userName").trim();
        String sPassword = request.getParameter("password").trim();
        String sRealname = request.getParameter("realName").trim();
        String sMobile = request.getParameter("mobile");
        String sSex = request.getParameter("xb_one");
        String sBrithday = request.getParameter("brithday").trim();
        String sQuestion = request.getParameter("question");
        String sAnswer = request.getParameter("answer").trim();
        //注册新用户
        MMailUser mRegister = new MMailUser();
        
        //如果用户是提交表单
        if ( sUsername != null && sUsername.length() > 0 )
        {	
        	MailUser mu =  new MailUser(sUsername,sPassword,sRealname,sBrithday,sSex,sMobile);
        	mu.setVquestion(sQuestion);
        	mu.setVanswer(sAnswer);
            boolean bCheckResult = mRegister.registerNewUser(mySession,mu);
            if ( bCheckResult )
            {
                response.sendRedirect("/webmails/registerSuccessful.jsp");
            }
            else
            {
                response.sendRedirect("/webmails/register.jsp");
            }
        }
        //如果用户非法进入这个页面
        else
        {
            response.sendRedirect("/webmails/index.jsp");
        }

		
	}
	
	

}

