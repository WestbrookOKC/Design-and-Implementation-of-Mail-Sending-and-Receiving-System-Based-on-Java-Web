package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.common.CommonInfo;
import com.mail.modeler.MAddress;
import com.mail.modeler.MMailUser;

public class CManageUser extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession mySession = req.getSession(true);
		
		String userName = (String)mySession.getAttribute("username");
		String sPower = (String)mySession.getAttribute("power");
		int power = Integer.parseInt(sPower);
		
		if(userName ==null||power!=CommonInfo.IS_SUPER){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		
		if(!req.getParameterNames().hasMoreElements()){
			mySession.setAttribute(CommonInfo.VIEWID_USERLIST, new Hashtable());
			MMailUser mmu = new MMailUser();
			mmu.getAllMailUserInfo(mySession);
			mySession.setAttribute("curPage", "manageuser");
			resp.sendRedirect("/webmails/manageUser.jsp");
			return;
		}
		
		String sSendMailToThisAddress = req.getParameter("addressIndex");
		if(sSendMailToThisAddress!=null&&sSendMailToThisAddress.length()>0){
			 //设置session中的详细页面值域
            mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable() );
            //获得对应的邮件人信息并跳转到发信页面
            MAddress mAddress = new MAddress();
            boolean bGetResult = mAddress.compositeMail( mySession, sSendMailToThisAddress );
            
            if ( bGetResult )
            {
                resp.sendRedirect("/webmails/composite.jsp");
            }
            else
            {
                resp.sendRedirect("/webmails/manageUser.jsp");
            }
            return;
		}
		
		 else
	        {
	            resp.sendRedirect("/webmails/index.jsp");
	        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
	

}
