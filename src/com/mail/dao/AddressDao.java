package com.mail.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailAddress;
/**
 * 
 *
 *	对于联系人邮件地址的操作接口，接口里只是定义了做什么，没有实现怎么做,实现接口的在com.mail.impl包下
 */
public interface AddressDao {
	public boolean insertAddressInfo(String userName,String friendName,String friendMail) throws SQLException;		//添加好友邮件地址
	
	public int isExist(String userName,String friendName) throws SQLException;		//判断好友是否存在
	
	public Vector<MailAddress> getFriendInfo(String userName) throws SQLException;	//获取好友的邮件地址等信息

	public boolean deleteAddressInfo(String userName,String friendMail)throws SQLException;		//删除好友信息
	
	public boolean updateAddressInfo(String userName,String friendName,String friendMail)throws SQLException;		//更改好友信息
}
