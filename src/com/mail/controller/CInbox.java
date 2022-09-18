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
import com.mail.modeler.MInbox;

/**
 * 
 * 
 * 收件箱页面的servlet
 */

public class CInbox extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			HttpSession mySession = req.getSession(true);
	        
	        //清空错误消息
	        mySession.setAttribute("errMsg","");
	        String sUserName = (String)mySession.getAttribute("username");
	        
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
	            mySession.setAttribute(CommonInfo.VIEWID_INBOXLIST, new Hashtable() );
	            //取得最新邮件
	            MInbox mInbox = new MInbox();
	            boolean b = false;
				try {
					b = mInbox.getNewMail(mySession);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			//获取新邮件，返回值为邮箱是否设置
	            if(b){												//如果邮箱已经设置好，就获取新邮件
	            	mySession.setAttribute("curPage","inbox");
	            	resp.sendRedirect("/webmails/inbox.jsp");		//跳转到邮件显示界面
	            	return;
	            }else{												//邮箱没有设置
	            	mySession.setAttribute("curPage","setting");	
	            	resp.sendRedirect("/webmails/setting.jsp");		//跳转到邮箱设置界面
	            	return;
	            }
	        }
	        
	        //得到用户输入信息
	        String sMailIndex = req.getParameter("mailIndex");				//这个值是经过inbox.js里面调用的函数设置的
	        String sMailOption = req.getParameter("mailOption");
	        
	        //如果用户是提交表单
	        if ( sMailIndex != null && sMailIndex.length() > 0 )
	        {
	            //设置session中的详细页面值域
	            mySession.setAttribute(CommonInfo.VIEWID_INBOXDETAIL, new Hashtable() );
	            //获得对应的邮件信息
	            MInbox mInbox = new MInbox();
	            boolean bGetResult = mInbox.getDetailMail( mySession, sMailIndex);   //查看收件箱邮件的详细信息
	            if ( bGetResult )
	            {
	                resp.sendRedirect("/webmails/inboxDetail.jsp");					//获取详细信息成功跳转到显示详细信息页面
	            }
	            else
	            {
	                resp.sendRedirect("/webmails/inbox.jsp");
	            }
	        }
	        //如果用户是从详细页面迁移过来的
	        else if ( sMailOption != null && sMailOption.length() > 0 )
	        {
	            String sSender = req.getParameter("sender");
	            String sSendTime = req.getParameter("sendTime");
	            String sSubject = req.getParameter("subject");
	            String sContent = req.getParameter("content");
	            //删除邮件
	            if ( sMailOption.equals("delete") )
	            {
	                MInbox mInbox = new MInbox();
	                boolean b = mInbox.deleteMail(mySession, sUserName, sSender, sSendTime);		//删除收件箱里的邮件
	                if(b){
	                	try {
							mInbox.getNewMail(mySession);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	resp.sendRedirect("/webmails/inbox.jsp");
	                	return;	                	
	                }else{
	                	return;
	                }
	            }
	            //回复邮件
	            else if (sMailOption.equals("reply") )
	            {
	                //设置session中的写邮件页面值域
	                mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable() );
	                MailContent mc = new MailContent();
	                mc.setSender(sSender);
	                mc.setSubject(sSubject);
	                mc.setContent(sContent);
	                MInbox mInbox = new MInbox();
	                mInbox.replyMail( mySession,mc );
	                mySession.setAttribute("curPage","composite");
	                resp.sendRedirect("/webmails/composite.jsp");
	                return;
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