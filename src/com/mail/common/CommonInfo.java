package com.mail.common;
/**
 * 
 * 
 *	整个程序用到的公共变量定义类
 */

public class CommonInfo {
	 //各画面的ID,作用是页面显示数据时通过哈希表设置和提取数据的，每个哈希表对应一个ID号
   public static String VIEWID_LOGIN          = "S0001";
   public static String VIEWID_REGISTER       = "S0002";
   public static String VIEWID_INBOXLIST      = "S0101";
   public static String VIEWID_INBOXDETAIL    = "S0102";
   public static String VIEWID_SENDBOXLIST    = "S0301";
   public static String VIEWID_SENDBOXDETAIL  = "S0302";
   public static String VIEWID_ADDRESSLIST    = "S0401";
   public static String VIEWID_SETTING        = "S0501";
   public static String VIEWID_HASSENDLIST    = "S0601";
   public static String VIEWID_HASDELETELIST  = "S0701";
   public static String VIEWID_FINDPASSWORD   = "S0801";
   public static String VIEWID_USERLIST       = "S0901";
   
   
   //oracle数据库相关常量
   public final static String ORACEL_DB_CONN_STRING = "jdbc:oracle:thin:@localhost:1521:XE";
   public final static String ORACEL_DB_USERNAME = "briup";
   public final static String ORACEL_DB_PASSWORD = "briup";
   
   //mysql数据库相关信息
   public final static String DB_DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
   public final static String DB_CONN_STRING      = "jdbc:mysql://localhost/db_email?user=root&password=123456"; 
   
   //邮件收发协议设置
   public final static String SEND_PROTOCOL = "smtp";
   //public final static String RECEIVE_PROTOCOL = "imap";
   public final static String RECEIVE_PROTOCOL = "pop3";
   
   //邮件是否被读标志位
   public final static int NOT_READFLG = 0;
   public final static int IS_READFLG = 1;
   
   //邮件是否是被删
	public final static int NOT_HISTORY =0;
	public final static int IS_HISTORY = 1;
	
	//邮件是否是草稿
	public final static int NOT_SEND = 0;
	public final static int IS_SEND = 1;
	
	//用户是否是超级管理员
	public final static int NOT_SUPER = 0;
	public final static int IS_SUPER = 1;
	
	//用户登录错误信息
	public final static int USER_NAME_ERROR = 1;
	public final static int PASSWORD_ERROR = 2;
	public final static int THE_USER_PAKING = 3;
	
	//找回密码方式
	public final static int FIND_BY_USERNAME = 1;
	public final static int FIND_BY_MOBILE = 2;
	
	public final static int NOT_PAKING = 0;
	public final static int IS_PAKING = 1;
}
