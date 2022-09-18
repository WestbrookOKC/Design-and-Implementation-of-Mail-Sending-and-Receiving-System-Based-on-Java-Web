package com.mail.modeler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpSession;

import com.mail.bean.MailInbox;
import com.mail.bean.MailContent;
import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.dao.InboxDao;
import com.mail.dao.MailSettingDao;
import com.mail.impl.InboxImpl;
import com.mail.impl.MailSettingImpl;
import com.mail.util.DButil;

public class MInbox {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getNewMail(HttpSession mySession){
		
		MailSettingDao msd = new MailSettingImpl();
		InboxDao ibd = new InboxImpl();
		MailSetting ms = null;
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_INBOXLIST);
		String sHost = "";
		String sUser = "";
		String sPass = "";
		String filename = "";
		String sUserName = (String)mySession.getAttribute("username");
		
		try {
			ms = msd.getMailSetting(sUserName);
			if(ms!=null){
				sHost = ms.getVimapIp();
				sUser = ms.getVimapUserName();
				sPass = ms.getVimapUserPassword();
			}else{
				mySession.setAttribute("errMsg","请首先进行邮箱设置！");
                return false;
			}
			//获取收件箱inbox邮件
			 Properties props = new Properties();
             props.put("mail.store.protocol", CommonInfo.RECEIVE_PROTOCOL);
             //props.put("mail.imap.class", "com.sun.mail.imap.IMAPStore");
             props.put("mail.pop3.class", "com.sun.mail.pop3.IMAPStore");
             props.put("mail.smtp.host", sHost); //设置发送邮件服务器      
             Session session = Session.getDefaultInstance(props);	//创建session对象
             Store store = session.getStore(CommonInfo.RECEIVE_PROTOCOL); //获取store对象
             
             session.setDebug(true);
              
             store.connect(sHost, sUser, sPass);
             //获取Folder,并以读写打开
             Folder folder = store.getFolder("INBOX");
             folder.open(Folder.READ_WRITE);
             //获取邮件
             Message[] msg = folder.getMessages();
             //将邮件写入数据库
             for(int i= 0;i<msg.length;i++){
            	 String sFrom = MailContent.getFrom(msg[i].getFrom()[0].toString());
            	 String sTo = MailContent.getFrom(msg[i].getRecipients(Message.RecipientType.TO)[0].toString());
            	 String sDate = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(msg[i].getSentDate());
            	 String sSubject = msg[i].getSubject();
            	 String sContent = "";
            	 if(msg[i].getContent() instanceof String){
            		 sContent =(String)msg[i].getContent();
            	 }else{
            	sContent = ((MimeMultipart)msg[i].getContent()).getBodyPart(0).getContent().toString();
            	System.out.println("------------------------------");
            		Multipart mp = (Multipart) msg[i].getContent();
            		BodyPart bPart = mp.getBodyPart(1);
            		System.out.println(bPart+"--------000000000000000-");
            		int count =mp.getCount();
            		System.out.println(count+"  cccccccccccc");
            		String  dispostion = bPart.getDisposition();
            		System.out.println(dispostion+"--------yyyyyyyyyyyyyyyyyyy");
            		 if((dispostion != null)&&(dispostion.equals(Part.ATTACHMENT)||dispostion.equals(Part.INLINE))){
                         filename =bPart.getFileName();
                         if(filename.toLowerCase().indexOf("gb2312")!=-1){
                             filename = MimeUtility.decodeText(filename);
                             System.out.println(filename );
                         }                    
            			InputStream in = bPart.getInputStream();
            			
            	         FileOutputStream writer = new FileOutputStream(new File("E:\\attachments\\" +filename));
            	         byte[] content = new byte[255];
            	         while ((in.read(content)) != -1)
            	         {
            	             writer.write(content);
            	         }
            	         writer.close();
            	         in.close();
            		}
            	 }
            	 
            	 //判断下这个邮件标题是否存在了不要重复插入 
            	 if(!ibd.checkSubjectExist(sUserName, sSubject)) {
            		 boolean b = ibd.insertMailToDB(new MailInbox(sUserName,sFrom,sTo,sDate,sSubject,sContent,CommonInfo.NOT_READFLG));
                	 if(b){
                		 DButil.getInstance().getCommit();
                	 }else{
                		 mySession.setAttribute("errMsg", "邮件插入数据库失败！");
                		 DButil.getInstance().getRollback();
                	 } 
            	 }
            	
             }
             
             //删除服务器上的邮件
             //for(int i=0;i<msg.length;i++){
            //	 msg[i].setFlag( Flags.Flag.DELETED, true );
             //}
             
             folder.close(true);
             store.close();
             //获取数据库的邮件重新显示在页面上
             Vector<MailContent> vMails = new Vector<MailContent>();
             Vector<MailInbox> vMailDB = ibd.selectByUserName(sUserName);
             for(int i=0;i<vMailDB.size();i++){
            	 MailContent mc = new MailContent();
            	 mc.setSender(vMailDB.get(i).getVseader());
            	 mc.setReseiver(vMailDB.get(i).getVreceiver());
            	 mc.setSendTime(vMailDB.get(i).getDsendTime());
            	 mc.setSubject(vMailDB.get(i).getVsubject());
            	 mc.setContent(vMailDB.get(i).getVcontent());
            	 mc.setReadFag(vMailDB.get(i).getReadFlg());
            	 vMails.add(mc);
             }
             myValues.put("mails", vMails);
        } catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "数据库错误！获取邮件设置失败");
			return false;
		} catch (NoSuchProviderException e) {
			mySession.setAttribute("errMsg", "初始化邮件服务器出现错误！");
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			mySession.setAttribute("errMsg", "读取邮件出现错误！");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			mySession.setAttribute("errMsg", "获取邮件出现错误！");
			e.printStackTrace();
			return false;
		} 
		return true;
		
		 
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getDetailMail( HttpSession mySession, String sMailIndex ){
    	InboxDao ibd = new InboxImpl();
    	//所有邮件的列表
        Hashtable listValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_INBOXLIST);
    
        //本页面使用的值
        Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_INBOXDETAIL);
        
        Vector<MailContent> mails = (Vector<MailContent>)listValues.get("mails");
        
        int iIndex = Integer.parseInt(sMailIndex);
        MailContent mailContent = mails.get(iIndex);
        
        myValues.put("mail", mailContent);
        
        int iReadFlg = mailContent.getReadFag();
        if(iReadFlg == CommonInfo.NOT_READFLG){
        	 String sUserName = (String)mySession.getAttribute("username");
             String sSender = mailContent.getSender();
             String sSendTime = mailContent.getSendTime();
             
             try {
				ibd.setInboxMailIsRead(sUserName, sSender, sSendTime);
				DButil.getInstance().getCommit();
			} catch (SQLException e) {
				e.printStackTrace();
				mySession.setAttribute("errMsg","阅读邮件时出现错误！");
                return false;

			}
        }
		return true;
    }
    
    //删除指定邮件
    public boolean deleteMail(HttpSession mySession,String userName,String sender,String sendTime){
    	InboxDao mbd = new InboxImpl();
    	
    	try {
			boolean b = mbd.setInboxMailTohistory(userName, sender, sendTime);
			if(b){
				DButil.getInstance().getCommit();
			}
		} catch (SQLException e) {
			try {
				DButil.getInstance().getRollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			mySession.setAttribute("errMsg","删除邮件时出现错误！");
            return false;
		}
		return true;
    	
    }
    
  //回复指定邮件
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean replyMail( HttpSession mySession, MailContent mail )
    {
        try
        {
            //本页面使用的值
            Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
            
            //如果是回复的话，应该明示邮件标题
            String sNewSubject = "";
            String sSubject = mail.getSubject();
            String sContent = mail.getContent();
            String sSender = mail.getSender();
            if ( sSubject.toUpperCase().startsWith("RE:") )
            {
                sNewSubject = sSubject;
            }
            else
            {
                sNewSubject = "Re:" + sSubject;
            }
           
            //改善：在原邮件内容每行的开头加上“> ”的符号
            String[] sContents = sContent.split("\r\n");
            StringBuffer sbContent = new StringBuffer();
            for ( int i=0; i<sContents.length; i++ )
            {
                sbContent.append( "\r\n> "+sContents[i]);
            }
            
            myValues.put( "receiver", sSender );
            myValues.put( "subject", sNewSubject );
            myValues.put( "content", sbContent.toString() );
            
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            mySession.setAttribute("errMsg","准备回复邮件时出现错误！");
            return false;
        }
        
    }
}