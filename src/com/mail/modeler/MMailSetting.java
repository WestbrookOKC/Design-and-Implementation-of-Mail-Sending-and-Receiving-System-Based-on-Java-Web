package com.mail.modeler;

import java.sql.SQLException;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.dao.MailSettingDao;
import com.mail.impl.MailSettingImpl;
import com.mail.util.DButil;
/**
 * 
 * 
 *	邮箱设置Model
 */
public class MMailSetting {
	
	/**
	 * 设置账户邮箱服务器信息
	 * @param mySession
	 * @param ms
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean registerSetting(HttpSession mySession,MailSetting ms){
		MailSettingDao msd = null;
		msd = new MailSettingImpl();
		//设置用户信息
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SETTING);
		myValues.put("imapIp",  ms.getVimapIp() );
        myValues.put("imapUser", ms.getVimapUserName());
        myValues.put("imapPass", ms.getVimapUserPassword());
        myValues.put("smtpIp",   ms.getVsmtpIp());
        myValues.put("smtpUser", ms.getVsmtpUserName());
        myValues.put("smtpPass", ms.getVsmtpUserPassword());
        myValues.put("address", ms.getVMailAddress());
        
        String sUserName = (String)mySession.getAttribute("username");
        try {
			msd.registerSetting(ms, sUserName);
			DButil.getInstance().getCommit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DButil.getInstance().getRollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			mySession.setAttribute("errMsg", "登录邮箱设置是出现错误！请重试！");
			return false;
		}		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getMailSettingInfo(HttpSession mySession){
		MailSettingDao msd = new MailSettingImpl();
		
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_SETTING);
		String sUserName = (String)mySession.getAttribute("username");
		try {
			MailSetting ms = msd.getMailSetting(sUserName);
			if(ms !=null){
				myValues.put("imapIp",   ms.getVimapIp() );
				myValues.put("imapUser", ms.getVimapUserName());
				myValues.put("imapPass", ms.getVimapUserPassword());
				myValues.put("smtpIp",   ms.getVsmtpIp());
				myValues.put("smtpUser", ms.getVsmtpUserName());
				myValues.put("smtpPass", ms.getVsmtpUserPassword());
				myValues.put("address", ms.getVMailAddress());
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "数据库错误,获取邮件设置失败！");
			return false;
		}
		
	}

}
