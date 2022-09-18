package com.mail.bean;

import java.io.Serializable;

/**
 * 
 * 邮件封装类,为了方便进行操作而进行封装的邮件类，该类中包含了一封邮件的基本信息
 */


public class MailContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sender;		//发送者
	private String reseiver;  //接收者
	/*sendTime的命名有些歧义，实际上它也用在发件箱中，
     *在发件箱的时候就表示保存时间，而不是发送时间了*/
	private String sendTime;
	private String subject;    //主题
	private String content;    //内容
	private int readFag;	  //是否被读
	private String affix;     //附件地址
    public String getAffix() {
		return affix;
	}
	public void setAffix(String affix) {
		this.affix = affix;
	}
	public String getAffixName() {
		return affixName;
	}
	public void setAffixName(String affixName) {
		this.affixName = affixName;
	}

	private String affixName; //附件名称
         

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReseiver() {
		return reseiver;
	}
	public void setReseiver(String reseiver) {
		this.reseiver = reseiver;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadFag() {
		return readFag;
	}
	public void setReadFag(int readFag) {
		this.readFag = readFag;
	}
	
	public static String getFrom( String orgFrom )
    {
        int iFrom = orgFrom.indexOf("<");
        int iTo = orgFrom.indexOf(">");
        
        if ( iFrom >= 0 && iTo >= 0)
        {
            return orgFrom.substring( iFrom+1, iTo );
        }
        return orgFrom;
    }
	
}
