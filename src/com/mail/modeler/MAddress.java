package com.mail.modeler;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.mail.bean.MailAddress;
import com.mail.bean.MailUser;
import com.mail.common.CommonInfo;
import com.mail.dao.AddressDao;
import com.mail.impl.AddressImpl;
import com.mail.util.DButil;
/**
 * 
 * 
 *	联系人的业务操作类，调用的是impl包下的AddressImpl已实现的一些操作获取或信息或添加信息，
 *  将获取的信息添加到一个哈希表里，本类里的方法一般返回值是布尔值
 */
public class MAddress {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	//插入联系人信息
	public boolean insertFriendInfo(HttpSession mySession,String friendName,String friendMail){
		//设置用户信息用
        Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_ADDRESSLIST);
        
        myValues.put( "friendName", friendName );
        myValues.put( "friendMail", friendMail );
        
		String sUserName = (String)mySession.getAttribute("username");
		
		AddressDao ad = new AddressImpl();
		
		try {
			int count = ad.isExist(sUserName, friendName);	//判断是否已经存在联系人
			if(count >0){
                mySession.setAttribute("errMsg","联系人已经存在！");
                return false;
			}
			boolean b = ad.insertAddressInfo(sUserName, friendName, friendMail);	//插入数据
			if(b){
				DButil.getInstance().getCommit();	   //插入成功，提交事务
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DButil.getInstance().getRollback();			//出现异常回滚事务
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			mySession.setAttribute("errMsg", "添加好友失败！");
			return false;
		}
		
		//既然已经保存成功，去掉页面相关信息
        myValues.put( "friendName", "" );
        myValues.put( "friendMail", "" );
		return true;
		
	}
	
	//获取好友信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getFriendMail ( HttpSession mySession ){
	
		//设置用户信息用
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_ADDRESSLIST);
		String sUsername = (String)mySession.getAttribute("username");
		AddressDao ad = new AddressImpl();
		try {
			Vector<MailAddress> friendInfos = ad.getFriendInfo(sUsername);
			myValues.put( "addresses", friendInfos );
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取好友信息失败！");
			return false;
		}
		return true;
		
	}
	
	//给好友发送邮件
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean compositeMail( HttpSession mySession, String sAddressIndex )
    {
        try
        {
        	Hashtable listValue = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_USERLIST);;
            //所有邮件的列表
            Hashtable listValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_ADDRESSLIST);
            //本页面使用的值
            Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
            if(listValue!=null&&listValue.size()>0){
            	Vector<MailUser> vAddresses = (Vector<MailUser>)listValue.get("users");
                int iIndex = Integer.parseInt(sAddressIndex);
                MailUser mus = (MailUser)vAddresses.get(iIndex);
            	myValues.put( "receiver", mus.getVuserName());
            }else if(listValues!=null&&listValues.size()>0){
            	Vector<MailAddress> vAddresses = (Vector<MailAddress>)listValues.get("addresses");
            	int iIndex = Integer.parseInt(sAddressIndex);
            	MailAddress mailAddress = (MailAddress)vAddresses.get(iIndex);
            	myValues.put( "receiver", mailAddress.getVfriendMail() );
           }
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            mySession.setAttribute("errMsg","尝试发送邮件时出现错误！");
            return false;
        }
    }
}
