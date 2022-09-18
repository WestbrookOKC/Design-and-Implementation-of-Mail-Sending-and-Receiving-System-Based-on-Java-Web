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

public class CLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		HttpSession mySession = req.getSession(true);
		
		mySession.setAttribute("errMsg", "");
		
		mySession.setAttribute(CommonInfo.VIEWID_LOGIN, new Hashtable());
		
		if(!req.getParameterNames().hasMoreElements()){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		String userName = req.getParameter("username").trim();
		String password = req.getParameter("password").trim();
		
		MMailUser ml = new MMailUser();
		boolean b = ml.checkUserInfo(mySession, userName, password);
		if(userName != null&& password.length() >0 ){
			if(b){
				mySession.setAttribute("curPage","inbox");
				resp.sendRedirect("/webmails/loginSuccessful.jsp");
			}else{
				resp.sendRedirect("/webmails/index.jsp");				
			}
			
		}else {
			resp.sendRedirect("/webmails/index.jsp");
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
