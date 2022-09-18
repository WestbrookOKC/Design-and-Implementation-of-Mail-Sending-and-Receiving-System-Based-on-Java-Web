package com.mail.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailContent;
import com.mail.bean.MailSendBox;
/**
 * 
 * 
 发件箱里对邮件所有操作接口
 */
public interface SendBoxDao {
	public boolean setSendMailIshistory(String userName,String saveTime) throws SQLException;		//删除发件箱里的邮件
	
	public boolean saveMail(String userName,MailContent mail) throws SQLException;					//保存邮件至收件箱
	
	public boolean deleteSendMail(String userName,String saveTime) throws SQLException;				//彻底删除收件箱里的邮件

	public boolean setHasSendMail(String userName,MailContent mail) throws SQLException;			//设置发件箱里的邮件为已发送
	
	public Vector<MailSendBox> selectMailByUserName(String userName)throws SQLException;			//获取发件箱里的所有邮件
	
	public Vector<MailSendBox> selectHasSendMailByUserName(String userName) throws SQLException; 	//获取发件箱箱里已发送邮件
	
	public Vector<MailSendBox> selectIshistoryByUserName(String userName) throws SQLException; 		//获取收件箱里的已删除邮件
	
	
}

