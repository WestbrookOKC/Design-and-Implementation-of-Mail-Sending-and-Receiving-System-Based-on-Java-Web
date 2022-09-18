package com.mail.bean;
/**
 * 
 * 收件箱类
 */

public class MailInbox {
	private int Nid;			//收件箱里的每封邮件的id
	private String VuserName;	//邮件系统的用户名
	private String Vseader;		//发送邮件者
	private String Vreceiver;	//邮件接收者
	private String DsendTime;	//邮件发送时间
	private String Vsubject;	//邮件主题
	private String Vcontent;	//邮件内容
	//private String vFile ;       // 邮件附件
	
	
//	public String getvFile() {
//		return vFile;
//	}
//
//	public void setvFile(String vFile) {
//		this.vFile = vFile;
//	}

	private int readFlg;		//邮件标记位(是否已读)
	
	public MailInbox() {
		
	}
	
	public MailInbox(String vuserName,String Vsender,String Vreceiverm,String DsendTime,String Vsubject,String Vcontent,int readFlg){
		this.VuserName = vuserName;
		this.Vseader =Vsender;
		this.Vreceiver = Vreceiverm;
		this.DsendTime = DsendTime;
		this.Vsubject = Vsubject;
		this.Vcontent = Vcontent;
		this.readFlg = readFlg;
		//this.vFile = vFile;
	}
	
	public MailInbox(int Nid,String Vsender,String Vreceiverm,String DsendTime,String Vsubject,String Vcontent,int readFlg){
		this.Nid = Nid;
		this.Vseader =Vsender;
		this.Vreceiver = Vreceiverm;
		this.DsendTime = DsendTime;
		this.Vsubject = Vsubject;
		this.Vcontent = Vcontent;
		this.readFlg = readFlg;
		//this.vFile = vFile;
	}
	
	public MailInbox(int Nid,String VuserName,String Vsender,String Vreceiverm,String DsendTime,String Vsubject,String Vcontent){
		this.Nid = Nid;
		this.VuserName = VuserName;
		this.Vseader =Vsender;
		this.Vreceiver = Vreceiverm;
		this.DsendTime = DsendTime;
		this.Vsubject = Vsubject;
		this.Vcontent = Vcontent;
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

	public String getVseader() {
		return Vseader;
	}

	public void setVseader(String vseader) {
		Vseader = vseader;
	}

	public String getVreceiver() {
		return Vreceiver;
	}

	public void setVreceiver(String vreceiver) {
		Vreceiver = vreceiver;
	}

	public String getDsendTime() {
		return DsendTime;
	}

	public void setDsendTime(String dsendTime) {
		DsendTime = dsendTime;
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

	public int getReadFlg() {
		return readFlg;
	}

	public void setReadFlg(int readFlg) {
		this.readFlg = readFlg;
	}

	
}
