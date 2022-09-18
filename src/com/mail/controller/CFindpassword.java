package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.common.CommonInfo;
import com.mail.modeler.MMailUser;
/**
 * 
 * 
 *	找回密码的servlet
 */
public class CFindpassword extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession mySession = req.getSession(true);
		
		mySession.setAttribute("errMsg", "");
		mySession.setAttribute(CommonInfo.VIEWID_FINDPASSWORD, new Hashtable());
		String sUserName_Mobile=(String)req.getParameter("userName_Mobile").trim();
		String answer = (String)req.getParameter("answer").trim();
		
		if(sUserName_Mobile !=null&&sUserName_Mobile.length()>1){
			MMailUser mfd = new MMailUser();
			boolean b = mfd.getQuestion(mySession, sUserName_Mobile);	//如果输入的用户名正确获取密保问题
			if(b){
				resp.sendRedirect("/webmails/findpassword.jsp");
				return;
			}
		}
		if(answer !=null&&answer.length()>0){
			MMailUser mfd = new MMailUser();
			boolean b = mfd.getPassword(mySession, answer, sUserName_Mobile);	//如果输入的答案正确，获取密码
			if(b){
				resp.sendRedirect("/webmails/findpassword.jsp");	
				return;
			}else{
				resp.sendRedirect("/webmails/findpassword.jsp");	
				return;
			}
		}
		
		else{
			resp.sendRedirect("/webmails/findpassword.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
