package com.mail.bean;
/**
 * 
 邮件设置类，设置发送邮件的一些信息，如发送邮件所需的imap和smtp的主机、
  用户名、密码；邮件地址信息,对应的页面是setting.jsp输入的信息
 */

public class MailSetting {
	private int Nid;						//数据库里的Id号，自动增长
	private String VuserName;  				 //邮箱设置对应的账户用户名
	private String VimapIp;					//imap主机地址
	private String VimapUserName;		//imap用户名
	private String VimapUserPassword;	//imap用户名对应的密码
	private String VsmtpIp;				//smtp主机地址
	private String VsmtpUserName;		//smtp用户名
	private String VsmtpUserPassword;	//smtp用户名对应的密码
	private String VMailAddress;		//以上imap和smtp对应的邮箱地址
	
	
	public static String DATABASETYPE = "mysql";
	
	public MailSetting() {
		// TODO Auto-generated constructor stub
	}
	
	public MailSetting(String VimapIp,String VimapUserName,String VimapUserPassword,String VsmtpIp,String VsmtpUserName,String VsmtpUserPassword,String VMailAddress){
		this.VimapIp = VimapIp;
		this.VimapUserName = VimapUserName;
		this.VimapUserPassword = VimapUserPassword;
		this.VsmtpIp = VsmtpIp;
		this.VsmtpUserName = VsmtpUserName;
		this.VsmtpUserPassword = VsmtpUserPassword;
		this.VMailAddress = VMailAddress;
	}
	
	public MailSetting(int Nid,String VuserName,String VimapIp,String VimapUserName,String VimapUserPassword,String VsmtpIp,String VsmtpUserName,String VsmtpUserPassword,String VMailAddress){
		this.Nid = Nid;
		this.VuserName = VuserName;
		this.VimapIp = VimapIp;
		this.VimapUserName = VimapUserName;
		this.VimapUserPassword = VimapUserPassword;
		this.VsmtpIp = VsmtpIp;
		this.VsmtpUserName = VsmtpUserName;
		this.VsmtpUserPassword = VsmtpUserPassword;
		this.VMailAddress = VMailAddress;
	}
	
	public int getNid() {
		return Nid;
	}
	public void setNid(int nid) {
		Nid = nid;
	}
	public String getVuserName() {
		return VuserName;
	}
	public void setVuserName(String vuserName) {
		VuserName = vuserName;
	}
	public String getVimapIp() {
		return VimapIp;
	}
	public void setVimapIp(String vimapIp) {
		VimapIp = vimapIp;
	}
	public String getVimapUserName() {
		return VimapUserName;
	}
	public void setVimapUserName(String vimapUserName) {
		VimapUserName = vimapUserName;
	}
	public String getVimapUserPassword() {
		return VimapUserPassword;
	}
	public void setVimapUserPassword(String vimapUserPassword) {
		VimapUserPassword = vimapUserPassword;
	}
	public String getVsmtpIp() {
		return VsmtpIp;
	}
	public void setVsmtpIp(String vsmtpIp) {
		VsmtpIp = vsmtpIp;
	}
	public String getVsmtpUserName() {
		return VsmtpUserName;
	}
	public void setVsmtpUserName(String vsmtpUserName) {
		VsmtpUserName = vsmtpUserName;
	}
	public String getVsmtpUserPassword() {
		return VsmtpUserPassword;
	}
	public void setVsmtpUserPassword(String vsmtpUserPassword) {
		VsmtpUserPassword = vsmtpUserPassword;
	}
	public String getVMailAddress() {
		return VMailAddress;
	}
	public void setVMailAddress(String vMailAddress) {
		VMailAddress = vMailAddress;
	}
}
