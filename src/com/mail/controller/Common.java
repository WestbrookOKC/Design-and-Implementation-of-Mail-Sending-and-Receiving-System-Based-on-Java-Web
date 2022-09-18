package com.mail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
 * 对共通的处理servlet类
 * 比如功能跳转，退出等功能
 */
public class Common extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession mySession = req.getSession(true);
		
		String sUserName = (String)mySession.getAttribute("username");
		
		req.setAttribute("errMsg", "");
		if(sUserName==null){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		if(!req.getParameterNames().hasMoreElements()){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		
		//得到用户输入信息
        String sGotoPage = req.getParameter("gotoPage");
        String sLogout = req.getParameter("logout");
        if(sLogout!=null&&sLogout.equals("yes")){
        	mySession.invalidate();
        	resp.sendRedirect("/webmails/index.jsp");
        	return;
        }if(sGotoPage!=null){
        	if(sGotoPage.equals("composite")){
        		mySession.setAttribute("curPage", "composite");
        		resp.sendRedirect("/webmails/controller/composite");
        		return;
        	}else if(sGotoPage.equals("sendbox")){
        		mySession.setAttribute("curPage", "sendbox");
        		resp.sendRedirect("/webmails/controller/sendbox");
        		return;
        	}else if(sGotoPage.equals("inbox")){
        		mySession.setAttribute("curPage", "inbox");
        		resp.sendRedirect("/webmails/controller/inbox");
        		return;
        	}else if(sGotoPage.equals("setting")){
        		mySession.setAttribute("curPage", "setting");
        		resp.sendRedirect("/webmails/controller/setting");
        		return;
        	}else if(sGotoPage.equals("address")){
        		mySession.setAttribute("curPage", "address");
        		resp.sendRedirect("/webmails/controller/address");
        		return;
        	}else if(sGotoPage.equals("changepassword")){
        		mySession.setAttribute("curPage", "changepassword");
        		resp.sendRedirect("/webmails/controller/changepassword");
        		return;
        	}else if(sGotoPage.equals("hassendbox")){
        		mySession.setAttribute("curPage", "hassendbox");
        		resp.sendRedirect("/webmails/controller/hassendbox");
        		return;
        	}else if(sGotoPage.equals("hasdeletebox")){
        		mySession.setAttribute("curPage", "hasdeletebox");
        		resp.sendRedirect("/webmails/controller/hasdeletebox");
        		return;
        	}else if(sGotoPage.equals("manageuser")){
        		mySession.setAttribute("curPage", "manageuser");
        		resp.sendRedirect("/webmails/controller/manageuser");
        		return;
        	}
        	else{
        		mySession.setAttribute("curPage", "login");
        		resp.sendRedirect("/webmails/index.jsp");
        		return;
        	}
        }else{
        	mySession.invalidate();
        	resp.sendRedirect("/webmails/index.jsp");
        	return;
        }
        
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
