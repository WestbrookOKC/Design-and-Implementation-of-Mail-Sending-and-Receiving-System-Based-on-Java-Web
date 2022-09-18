package com.mail.modeler;

import com.mail.bean.MailContent;
import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.dao.MailSettingDao;
import com.mail.dao.SendBoxDao;
import com.mail.impl.MailSettingImpl;
import com.mail.impl.SendBoxImpl;
import com.mail.util.DButil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

public class MComposite{
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean sendMail(HttpSession mySession,MailContent mail) throws UnsupportedEncodingException{
		
		
		
		
	
		
		System.out.println(mail.getAffix()+"  &&&&&&&&&&&&&&&&&&&&&");
		  
		String sUserName = (String)mySession.getAttribute("username");
		//设置用户信息用
        Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
        //myValues.put( "sender",mail.getSender());
        myValues.put( "receiver", mail.getReseiver() );
        myValues.put( "subject", mail.getSubject() );
        myValues.put( "content", mail.getContent() );
        myValues.put( "saveTime", mail.getSendTime());
        //myValues.put( "affix", mail.getAffix());
        System.out.println(mail.getReseiver()+"+++++++++++");
        System.out.println(mail.getAffix()+"----------");
        
        String sHost = "";
        String sUser = "";
        String sPass = "";
        String sAddress = "";
        Session session = null;
        MailSetting ms = null;
		MailSettingDao msd=new MailSettingImpl();
		SendBoxDao sbd = new SendBoxImpl();
        try {
			ms = msd.getMailSetting(sUserName);
			if(ms!=null){
				sHost = ms.getVsmtpIp();
				sUser = ms.getVsmtpUserName();				
				sPass = ms.getVsmtpUserPassword();
				
				
		       // session.setDebug(true); 
		        
				sAddress = ms.getVMailAddress();
			}else{
				mySession.setAttribute("errMsg", "请先设置邮箱信息！");
				return false;
			}
			Properties props = new Properties();
			props.put("mail.transport.protocol", CommonInfo.SEND_PROTOCOL);
			props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
			props.put("mail.smtp.auth", "true");   //这样才能通过验证
			props.put("mail.smtp.host", sHost); //设置发送邮件服务器
			Authenticator authenticator = new MyAuthenticator(sUser, sPass); 
			 
			session = Session.getInstance(props,authenticator);
			session.setDebug(true);		//输出跟踪日记
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sAddress));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReseiver()));
			 // Message.RecipientType.TO属性表示接收者的类型为TO 
			if(mail.getAffix()==null){
				message.setSubject(mail.getSubject());
				message.setText(mail.getContent());
				message.setSentDate(new Date());
			}
			else{
			message.setSubject(mail.getSubject());

                       // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                             Multipart multipart = new MimeMultipart(); 

                        //设置邮件的文本内容
                        BodyPart contentPart = new MimeBodyPart();
                         contentPart.setText(mail.getContent());
                           multipart.addBodyPart(contentPart);

             //添加附件
              BodyPart messageBodyPart= new MimeBodyPart();
                 DataSource source = new FileDataSource("E:/FuJianFile/"+mail.getAffix());
                 
            //添加附件的内容
                    messageBodyPart.setDataHandler(new DataHandler(source));
            //添加附件的标题
            //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            messageBodyPart.setFileName(source.getName());
            multipart.addBodyPart(messageBodyPart);
            
                        //将multipart对象放到message中
                     message.setContent(multipart);

                    message.setSentDate(new Date()); 
			}
                       //保存邮件
                     message.saveChanges();

			
		      
						
			Transport transport = session.getTransport(CommonInfo.SEND_PROTOCOL);
			transport.connect(sHost, sUser, sPass);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
			

//            
//            
			//发送成功，如果存在的话删除数据库里的发件箱邮件
			if(mail.getSendTime().equals("")){
				mail.setSendTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		}
			boolean b = sbd.setHasSendMail(sUserName, mail);
//			boolean b = sbd.deleteHasSendMail(sUserName, mail.getSendTime());
			if(b){
				DButil.getInstance().getCommit();
			}else{
				DButil.getInstance().getRollback();
				return false;
			}
			
        } catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取邮件设置失败！");
			return false;
		} catch (AddressException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "设置邮件地址失败！");
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			
			return false;
		} 
		return true;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean saveMail(HttpSession mySession,MailContent mail){
		
		String sUserName = (String)mySession.getAttribute("username");
		
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SENDBOXDETAIL);
		myValues.put("receiver", mail.getReseiver());
		myValues.put("subject", mail.getSubject());
		myValues.put("content", mail.getContent());
		//myValues.put("affix", mail.getAffix());
		SendBoxDao sbd = new SendBoxImpl();
		//获得当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String sNewSaveTime = sdf.format( new Date(System.currentTimeMillis()));
        
        
        try {
			sbd.deleteSendMail(sUserName, mail.getSendTime());
			
			mail.setSendTime(sNewSaveTime);
			
			boolean sb = sbd.saveMail(sUserName, mail);
			if(sb){
				DButil.getInstance().getCommit();
			}else{
				DButil.getInstance().getRollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "保存邮件出现错误！");
			return false;
		}
		return true;
	}
}