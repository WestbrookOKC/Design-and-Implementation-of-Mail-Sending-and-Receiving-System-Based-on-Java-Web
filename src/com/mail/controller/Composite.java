package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.bean.MailContent;
import com.mail.common.CommonInfo;
import com.mail.modeler.MComposite;

public class Composite extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession mySession = req.getSession(true);
		
		String  sUserName = (String)mySession.getAttribute("username");
		mySession.setAttribute("errMsg", "");
		
		//是否非法进入本页面
        if (  sUserName == null )
        {
            resp.sendRedirect("/webmails/index.jsp");
            return;
        }
        //是否进入默认页面
        if ( !req.getParameterNames().hasMoreElements() )
        {
            //设置session中的页面值域
            mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable() );
            mySession.setAttribute("curPage","composite");
            resp.sendRedirect("/webmails/composite.jsp");
            return;
        }
        
        //得到用户输入信息
        String sReceiver = req.getParameter("receiver");
        String sSubject  = req.getParameter("subject");
        String sContent = req.getParameter("content");
        String sMailOption = req.getParameter("mailOption");
        String sSaveTime = req.getParameter("saveTime");
        String sAffix = req.getParameter("affix");
        MailContent mail= null;
        //如果用户是提交表单
        	if ( sMailOption != null && sMailOption.length() > 0 )
        	{
        		//设置session中的页面值域
        		mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable());
        		//如果是发送邮件
        		if ( sMailOption.equals("send") )
        		{
        			
        			MComposite mComposite = new MComposite();
        			mail = new MailContent();
        			mail.setReseiver(sReceiver);
        			mail.setSubject(sSubject);
        			mail.setSendTime(sSaveTime);
        			mail.setContent(sContent);
        			mail.setAffix(sAffix);
        			boolean b = mComposite.sendMail( mySession,mail);
        			
        			if (b)
        			{
        				resp.sendRedirect("/webmails/sendSuccessful.jsp");
        			}
        			else
        			{
        				resp.sendRedirect("/webmails/composite.jsp");
        			}
        		}
        		//如果是存储邮件
        		else if ( sMailOption.equals("save") )
        		{
        			MComposite mComposite = new MComposite();
        			mail = new MailContent();
        			mail.setReseiver(sReceiver);
        			mail.setSubject(sSubject);
        			mail.setContent(sContent);
        			mail.setSendTime(sSaveTime);
        			boolean bSendResult = mComposite.saveMail( mySession,mail);
        			if ( bSendResult )
        			{
        				resp.sendRedirect("/webmails/saveSuccessful.jsp");
        			}
        			else
        			{
        				resp.sendRedirect("/webmails/composite.jsp");
        			}
        		}
        	}
        	//如果用户非法进入这个页面
        	else
        	{
        		resp.sendRedirect("/webmails/index.jsp");
        	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}

