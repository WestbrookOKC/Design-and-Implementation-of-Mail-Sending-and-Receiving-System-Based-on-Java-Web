package com.mail.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailInbox;
/**
 * 
 * 
 *	收件箱里对邮件操作的接口
 */
public interface InboxDao {
	public boolean insertMailToDB(MailInbox inbox) throws SQLException;		   //从磁盘获取邮件读入数据库里
	public Vector<MailInbox> selectByUserName(String userName) throws SQLException;   //通过用户名获取邮件
	public boolean setInboxMailIsRead(String userName,String sender,String sendTime) throws SQLException;	//更改邮件是否已读
	public boolean setInboxMailTohistory(String userName,String sender,String sendTime) throws SQLException;     //删除邮件，只是更改了标志位
	public Vector<MailInbox> selectIshistoryByUserName(String userName) throws SQLException;          //获取已删除的邮件
	public boolean deleteInboxMail(String userName,String sender,String sendTime,String subject) throws SQLException;	//彻底删除收件箱里的邮件
	/*
	 * 判断收件箱中是否已经添加了该邮件
	 */
	public boolean checkSubjectExist(String userName,String subject) throws SQLException;
		
}
