package com.mail.modeler;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.mail.bean.MailContent;
import com.mail.bean.MailInbox;
import com.mail.bean.MailSendBox;
import com.mail.common.CommonInfo;
import com.mail.dao.InboxDao;
import com.mail.dao.SendBoxDao;
import com.mail.impl.InboxImpl;
import com.mail.impl.SendBoxImpl;

public class MSendBox {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getSaveMail(HttpSession mySession){
		
		String sUserName = (String)mySession.getAttribute("username");
		//设置用户信息用
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXLIST);
		
		MailContent mc = null;
		Vector<MailContent> vMails = null;
		SendBoxDao sbd = null;
		try {
			vMails = new Vector<MailContent>();
			sbd = new SendBoxImpl();
			Vector<MailSendBox> mail = sbd.selectMailByUserName(sUserName);
			for(int i=0;i<mail.size();i++){
				mc = new MailContent();
				mc.setReseiver(mail.get(i).getVreceiver());
				mc.setSubject(mail.get(i).getVsubject());
				mc.setSendTime(mail.get(i).getVsaveTime());
				mc.setContent(mail.get(i).getVcontent());
				vMails.add(mc);
			}
			myValues.put("mail", vMails);
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取邮件失败！");
			return true;
		}
		
		return true;
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getHasSendMail(HttpSession mySession){
		String sUserName = (String)mySession.getAttribute("username");
		MailContent mc = null;
		Vector<MailContent> vMails = null;
		SendBoxDao sbd = new SendBoxImpl();
		try {
			vMails = new Vector<MailContent>();
			Hashtable myValues=(Hashtable)mySession.getAttribute(CommonInfo.VIEWID_HASSENDLIST);
			Vector<MailSendBox> mail = sbd.selectHasSendMailByUserName(sUserName);
			for(int i=0;i<mail.size();i++){
				mc = new MailContent();
				mc.setReseiver(mail.get(i).getVreceiver());
				mc.setSubject(mail.get(i).getVsubject());
				mc.setSendTime(mail.get(i).getVsaveTime());
				mc.setContent(mail.get(i).getVcontent());
				vMails.add(mc);
			}
			myValues.put("mail", vMails);
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取已发送邮件失败！");
			return false;
		}
		return true;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getHasDeleteMail(HttpSession mySession){
		
		String sUserName = (String)mySession.getAttribute("username");
		Vector<MailSendBox> sbmList = null;
		Vector<MailInbox> ibmList = null;
		Vector<MailContent> vMails = null;
		MailContent mc = null;
		SendBoxDao sbd = new SendBoxImpl();
		InboxDao ibd = new InboxImpl();
		
		try {
			vMails = new Vector<MailContent>();
			Hashtable myValues=(Hashtable)mySession.getAttribute(CommonInfo.VIEWID_HASDELETELIST);
			sbmList = sbd.selectIshistoryByUserName(sUserName);
			ibmList = ibd.selectIshistoryByUserName(sUserName);
			for(int i=0;i<sbmList.size();i++){
				mc = new MailContent();
				mc.setReseiver(sbmList.get(i).getVreceiver());
				mc.setSubject(sbmList.get(i).getVsubject());
				mc.setSendTime(sbmList.get(i).getVsaveTime());
				mc.setContent(sbmList.get(i).getVcontent());
				vMails.add(mc);
			}
			for(int i=0;i<ibmList.size();i++){
				mc = new MailContent();
				mc.setReseiver(ibmList.get(i).getVreceiver());
				mc.setSubject(ibmList.get(i).getVsubject());
				mc.setSendTime(ibmList.get(i).getDsendTime());
				mc.setContent(ibmList.get(i).getVcontent());
				vMails.add(mc);
			}
			myValues.put("mail", vMails);
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取删除的邮件失败！");
			return false;
		}
		
		
		return true;
	}
	
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean compositeMail( HttpSession mySession, String sMailIndex )
	    {
	        try
	        {
	            //所有邮件的列表
	            Hashtable listValues = null;
	            Object objNotsend = mySession.getAttribute(CommonInfo.VIEWID_SENDBOXLIST);
	            Object objIssend = mySession.getAttribute(CommonInfo.VIEWID_HASSENDLIST);
	            Object objDelete = mySession.getAttribute(CommonInfo.VIEWID_HASDELETELIST);
	            if(objNotsend!=null){
	            	listValues = (Hashtable)objNotsend;
	            }
	            if(objIssend!=null){
	            	listValues = (Hashtable)objIssend;
	            }
	            if(objDelete!=null){
	            	listValues = (Hashtable)objDelete;
	            }
	        
	            //本页面使用的值
	            Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
	        
	            Vector<MailContent> vMails = (Vector<MailContent>)listValues.get("mail");
	        
	            int iIndex = Integer.parseInt(sMailIndex);
	            MailContent mailContent = (MailContent)vMails.get(iIndex);
	            
	            myValues.put( "receiver", mailContent.getReseiver());
	            myValues.put( "subject", mailContent.getSubject() );
	            myValues.put( "content", mailContent.getContent() );
	            myValues.put( "saveTime", mailContent.getSendTime() );
	            
	            return true;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            mySession.setAttribute("errMsg","显示邮件时出现错误！");
	            return false;
	        }
	    }
}