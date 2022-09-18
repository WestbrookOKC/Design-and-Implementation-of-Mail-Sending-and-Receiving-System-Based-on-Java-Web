package com.mail.bean;

public class MailMsg {
	private int Nid;
	private String VuserName;
	private String VmsgContent;
	private String VmsgDate;
	
	public MailMsg() {
		// TODO Auto-generated constructor stub
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
	public String getVmsgContent() {
		return VmsgContent;
	}
	public void setVmsgContent(String vmsgContent) {
		VmsgContent = vmsgContent;
	}
	public String getVmsgDate() {
		return VmsgDate;
	}
	public void setVmsgDate(String vmsgDate) {
		VmsgDate = vmsgDate;
	}
	
}
