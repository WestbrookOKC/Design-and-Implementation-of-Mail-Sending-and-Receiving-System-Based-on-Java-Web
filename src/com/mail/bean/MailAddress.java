package com.mail.bean;
/**
 * 
 * 
 *联系人邮箱地址类、对应的数据库表是t_maddress
 */

public class MailAddress {
	private int Nid;			//联系人地址Id,数据库里自动增长
	private String VuserName;	//添加该联系人的用户用户名
	private String VfriendName;	//联系人的姓名
	private String VfriendMail;	//联系人的邮箱
	
	public MailAddress() {
		// TODO Auto-generated constructor stub
	}
	
	public MailAddress(String VuserName,String VfriendName,String VfriendMail){
		this.VuserName = VuserName;
		this.VfriendName = VfriendName;
		this.VfriendMail = VfriendMail;
	}
	
	public MailAddress(int Nid,String VfriendName,String VfriendMail){
		this.Nid = Nid;
		this.VfriendName = VfriendName;
		this.VfriendMail = VfriendMail;
	}
	
	public MailAddress(int Nid,String VuserName,String VfriendName,String VfriendMail){
		this.Nid = Nid;
		this.VuserName = VuserName;
		this.VfriendName = VfriendName;
		this.VfriendMail = VfriendMail;
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

	public String getVfriendName() {
		return VfriendName;
	}

	public void setVfriendName(String vfriendName) {
		VfriendName = vfriendName;
	}

	public String getVfriendMail() {
		return VfriendMail;
	}

	public void setVfriendMail(String vfriendMail) {
		VfriendMail = vfriendMail;
	}
	
}
