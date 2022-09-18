package com.mail.bean;
/**
 * 
 *发件箱类
 */

public class MailSendBox {
	private int Nid;			//发件箱每封邮件的Id
	private String VuserName;	//邮件系统用户名
	private String Vreceiver;	//邮件接收者
	private String Vsubject;	//主题
	private String Vcontent;	//邮件内容
	private String VsaveTime;	//邮件保存时的时间
	public MailSendBox() {
		// TODO Auto-generated constructor stub
	}
	
	public MailSendBox(int Nid,String Vreceiver,String Vsubject,String Vcontent,String VsaveTime){
		this.Nid = Nid;
		this.Vreceiver = Vreceiver;
		this.Vsubject = Vsubject;
		this.Vcontent = Vcontent;
		this.VsaveTime = VsaveTime;
	}
	
	public MailSendBox(int Nid,String VuserName,String Vreceiver,String Vsubject,String Vcontent,String VsaveTime){
		this.Nid = Nid;
		this.VuserName = VuserName;
		this.Vreceiver = Vreceiver;
		this.Vsubject = Vsubject;
		this.Vcontent = Vcontent;
		this.VsaveTime = VsaveTime;
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
	public String getVreceiver() {
		return Vreceiver;
	}
	public void setVreceiver(String vreceiver) {
		Vreceiver = vreceiver;
	}
	public String getVsubject() {
		return Vsubject;
	}
	public void setVsubject(String vsubject) {
		Vsubject = vsubject;
	}
	public String getVcontent() {
		return Vcontent;
	}
	public void setVcontent(String vcontent) {
		Vcontent = vcontent;
	}
	public String getVsaveTime() {
		return VsaveTime;
	}
	public void setVsaveTime(String vsaveTime) {
		VsaveTime = vsaveTime;
	}
	
	
}
