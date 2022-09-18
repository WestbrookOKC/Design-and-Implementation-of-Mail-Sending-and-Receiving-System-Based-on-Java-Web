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

/**
 * 
 *
 *	联系人servlet类，address.jsp页面表单提交到这里进行处理,主要是控制页面的跳转
 **/
public class CAddress extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession mySession = req.getSession(true);	//获取会话session
        
        //清空错误消息
        mySession.setAttribute("errMsg","");
        
        //是否非法进入本页面
        if ( mySession.getAttribute("username") == null )
        {
            resp.sendRedirect("/webmails/index.jsp");
            return;
        }
        //是否进入默认页面，就是进入页面时没有任何的数据提交过来（没有表单提交）
        if ( !req.getParameterNames().hasMoreElements() )
        {
            //设置session中的页面值域
            mySession.setAttribute(CommonInfo.VIEWID_ADDRESSLIST, new Hashtable() );
            //取得所有联系人的邮件地址列表
            MAddress mAddress = new MAddress();
            mAddress.getFriendMail( mySession );//调用modeler包里的Maddress类里的getFriendMail方法获取联系人信息
            mySession.setAttribute("curPage","address");
            resp.sendRedirect("/webmails/address.jsp");
            return;
        }
        
        //有表单提交时，得到用户输入信息
        String sAddressIndex = req.getParameter("addressIndex");
        String sFriendName = req.getParameter("friendName");
        String sFriendMail = req.getParameter("friendMail");
        
        //如果用户是想给该联系人发送邮件
        if ( sAddressIndex != null && sAddressIndex.length() > 0 )
        {
            //设置session中的详细页面值域
            mySession.setAttribute(CommonInfo.VIEWID_SENDBOXDETAIL, new Hashtable() );
            //获得对应的邮件人信息并跳转到发信页面
            MAddress mAddress = new MAddress();
            boolean bGetResult = mAddress.compositeMail( mySession, sAddressIndex );
            
            if ( bGetResult )
            {
                resp.sendRedirect("/webmails/composite.jsp");
            }
            else
            {
                resp.sendRedirect("/webmails/address.jsp");
            }
            return;
        }
        
        //如果用户添加联系人信息
        if ( sFriendName != null && sFriendName.length() > 0 )
        {
            //登录对应的联系人信息并重新显示本页面
            MAddress mAddress = new MAddress();
            boolean bGetResult = mAddress.insertFriendInfo( mySession, sFriendName, sFriendMail );
            
            if ( bGetResult )
            {
                //如果成功了，应该重新取得联系人列表
                mySession.setAttribute(CommonInfo.VIEWID_ADDRESSLIST, new Hashtable() );
                mAddress.getFriendMail( mySession );
            }

            resp.sendRedirect("/webmails/address.jsp");
            
            return;
        }
        //管理员锁定用户
        String sTheUserName = req.getParameter("theAddress");
        if(sTheUserName!=null&&sTheUserName.length()>0){
        	MMailUser mmu = new MMailUser();
        	boolean b = mmu.pakingUserByUserName(mySession, sTheUserName);
        	if(b){
        		mySession.setAttribute(CommonInfo.VIEWID_USERLIST, new Hashtable());
        		mmu.getAllMailUserInfo(mySession);
        		resp.sendRedirect("/webmails/manageUser.jsp");
        	}else{
        		resp.sendRedirect("/webmails/manageUser.jsp");
        	}
        	 return;
        }
        //管理员解锁用户
        String sRemoveUserName = req.getParameter("removepaking");
        if(sRemoveUserName!=null&&sRemoveUserName.length()>0){
        	MMailUser mmu = new MMailUser();
        	boolean b = mmu.removePakingUserByUserName(mySession, sRemoveUserName);
        	if(b){
        		mySession.setAttribute(CommonInfo.VIEWID_USERLIST, new Hashtable());
        		mmu.getAllMailUserInfo(mySession);
        		resp.sendRedirect("/webmails/manageUser.jsp");
        	}else{
        		resp.sendRedirect("/webmails/manageUser.jsp");
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
