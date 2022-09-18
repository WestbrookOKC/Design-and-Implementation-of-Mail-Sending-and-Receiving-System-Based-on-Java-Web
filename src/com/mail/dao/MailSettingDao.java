package com.mail.dao;

import java.sql.SQLException;

import com.mail.bean.MailSetting;

/**
 * 
 * 
 *	邮件系统里账户的邮箱设置接口
 */
public interface MailSettingDao {
	public MailSetting registerSetting(MailSetting mailSetting,String userName) throws SQLException;   //注册后的邮箱设置
	
	public MailSetting getMailSetting(String userName) throws SQLException;    //获取邮件设置信息，显示在设置页面
	
	public int hasSetting(String userName)throws SQLException;				//判断是否已经设置
	
}
