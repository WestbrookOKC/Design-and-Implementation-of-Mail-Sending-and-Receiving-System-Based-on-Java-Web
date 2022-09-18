package com.mail.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailUser;
/**
 * 
 * 
 * 用户接口
 */
public interface UserDao {
	
	//用户注册
	public MailUser register(MailUser user) throws SQLException;       										
	
	//通过用户名和密码获取用户真实姓名
	public MailUser selectUserByUserNameAndPassword(String userName,String password)throws SQLException;	
	
	//查找用户信息
	public MailUser findMailUserByUserName(MailUser user) throws SQLException;								
	
	//获取用户信息
	public Vector<MailUser> getMailUserInfo() throws SQLException;	
	
	//检查用户是否存在	
	public int isExist(MailUser user) throws SQLException;  
	
	//登陆验证	
	public int checkUser(MailUser user) throws SQLException;   
	
	//更改密码	
	public boolean changePassword(String userName,String newPassword)throws SQLException;					
	
	//删除用户信息，没有彻底删除只是更改了标志位	
	public boolean isPakingMailUserByUserName(int ispaking,String userName) throws SQLException;	
	
	//获取旧密码,用以更改密码是旧密码的输入是否正确	
	public String getOldPassword(String userName)throws SQLException;	
	
	//获取用户的密保问题
	public String findMailUserPasswordQuestion(int type,String userName) throws SQLException;	
	
	//根据输入的答案找回密码
	public String findPasswordByAnwer(String answer,String userName) throws SQLException;					
}