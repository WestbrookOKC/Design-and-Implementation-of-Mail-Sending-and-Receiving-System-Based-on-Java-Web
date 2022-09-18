package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.modeler.MMailSetting;

public class CMailSetting extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession mySession = req.getSession(true);
		
		mySession.setAttribute("errMsg", "");
		
		mySession.setAttribute(CommonInfo.VIEWID_SETTING, new Hashtable());
		
		String userName = (String)mySession.getAttribute("username");
		if(userName ==null){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		
		//是否进入了默认页面
		if(!req.getParameterNames().hasMoreElements()){
			MMailSetting ms = new MMailSetting();
			ms.getMailSettingInfo(mySession);
			mySession.setAttribute("curPage","setting");
			resp.sendRedirect("/webmails/setting.jsp");
			return;
		}
		
		//得到用户输入的信息
		String sImapIp = req.getParameter("imapIp");
		String sImapUserName = req.getParameter("imapUser");
		String sImapPassword = req.getParameter("imapPass");
		
		String sSmtpIp = req.getParameter("smtpIp");
		String sSmtpUserName = req.getParameter("smtpUser");
		String sSmtpPassword = req.getParameter("smtpPass");
		
		String sAddress = req.getParameter("address");
		
		//用户提交表单	
		if(sImapIp !=null&&sImapIp.length()>0){
			MMailSetting ms = new MMailSetting();
			boolean b = ms.registerSetting(mySession, new MailSetting(sImapIp,sImapUserName,sImapPassword,sSmtpIp,sSmtpUserName,sSmtpPassword,sAddress));
			if(b){
				resp.sendRedirect("/webmails/settingSuccessful.jsp");
			}else{
				resp.sendRedirect("/webmails/setting.jsp");
			}
		}else{
			resp.sendRedirect("/webmails/index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
