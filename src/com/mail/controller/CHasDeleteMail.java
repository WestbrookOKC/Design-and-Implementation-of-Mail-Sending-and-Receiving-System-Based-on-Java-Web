package com.mail.controller;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mail.common.CommonInfo;
import com.mail.modeler.MSendBox;
/**
 * 
 * 
 *	已删除邮件的servlet
 */
public class CHasDeleteMail extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession mySession = req.getSession(true);
		mySession.setAttribute("errMsg", "");
		
		String sUserName = (String)mySession.getAttribute("username");
		
		if(sUserName == null){
			resp.sendRedirect("/webmails/index.jsp");
			return;
		}
		
		 //是否进入默认页面
        if ( !req.getParameterNames().hasMoreElements() )
        {
            //设置session中的页面值域
            mySession.setAttribute(CommonInfo.VIEWID_HASDELETELIST, new Hashtable());
            //取得最新邮件
            MSendBox mSendBox = new MSendBox();
            mSendBox.getHasDeleteMail(mySession);                //获取邮件
            mySession.setAttribute("curPage","hasdeletebox");
            resp.sendRedirect("/webmails/hasDelete.jsp");        //跳转到已删除邮件显示界面
            return;
        }
        
        //得到用户输入信息
        String sMailIndex = req.getParameter("mailIndex");
        
        //如果用户是提交表单
        if ( sMailIndex != null && sMailIndex.length() > 0 )
        {
            //设置session中的详细页面值域
            mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable() );
            //获得对应的邮件信息
            MSendBox mSendBox = new MSendBox();
            boolean b = mSendBox.compositeMail( mySession, sMailIndex );
            
            if (b)
            {
                resp.sendRedirect("/webmails/inboxDetail.jsp");
            }
            else
            {
                resp.sendRedirect("/webmails/hasDelete.jsp");
            }
            return;
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