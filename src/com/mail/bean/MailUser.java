package com.mail.bean;
/**
 * 
    邮件系统用户类
 */
public class MailUser {
	private int Nid;			//用户的Id
	private String VuserName;	//用户名
	private String VpassWord;	//密码	
	private String VrealName;	//真实姓名
	private String Dbirthday;	//生日
	private String Vsex;		//性别
	private String Nmobile;		//手机
	private String Vquestion;	//密码保护问题
	private String Vanswer;		//密码保护答案
	private String power;		//用户的权限标志位
	private int ispacking;		//用户是否被锁定
	
	public MailUser() {

	}
	
	public MailUser(String VuserName,String VpassWord,String VrealName,String Dbirthday,String Vsex,String Nmobile){
		this.VuserName = VuserName;
		this.VpassWord = VpassWord;
		this.VrealName = VrealName;
		this.Dbirthday = Dbirthday;
		this.Vsex = Vsex;
		this.Nmobile = Nmobile;
	}
	
	public MailUser(int Nid,String VuserName,String VpassWord,String VrealName,String Dbirthday,String Vsex,String Nmobile){
		this.Nid = Nid;
		this.VuserName = VuserName;
		this.VpassWord = VpassWord;
		this.VrealName = VrealName;
		this.Dbirthday = Dbirthday;
		this.Vsex = Vsex;
		this.Nmobile = Nmobile;
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
	public String getVpassWord() {
		return VpassWord;
	}
	public void setVpassWord(String vpassWord) {
		VpassWord = vpassWord;
	}
	public String getVrealName() {
		return VrealName;
	}
	public void setVrealName(String vrealName) {
		VrealName = vrealName;
	}
	public String getDbirthday() {
		return Dbirthday;
	}
	public void setDbirthday(String dbirthday) {
		Dbirthday = dbirthday;
	}
	public String getVsex() {
		return Vsex;
	}
	public void setVsex(String vsex) {
		Vsex = vsex;
	}

	public String getNmobile() {
		return Nmobile;
	}

	public void setNmobile(String nmobile) {
		Nmobile = nmobile;
	}

	public String getVquestion() {
		return Vquestion;
	}

	public void setVquestion(String vquestion) {
		Vquestion = vquestion;
	}

	public String getVanswer() {
		return Vanswer;
	}

	public void setVanswer(String vanswer) {
		Vanswer = vanswer;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public int getIspacking() {
		return ispacking;
	}

	public void setIspacking(int ispacking) {
		this.ispacking = ispacking;
	}

}
