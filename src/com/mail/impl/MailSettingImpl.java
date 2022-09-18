package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mail.bean.MailSetting;
import com.mail.dao.MailSettingDao;
import com.mail.util.DButil;

public class MailSettingImpl implements MailSettingDao{

	@Override
	public MailSetting registerSetting(MailSetting mailSetting,String userName) throws SQLException {
		int count = hasSetting(userName);
		String sql = "";
		if(count>0){
			sql = "update t_mmailsetting set imapip=?,imapusername=?,imappassword=?,smtpip=?,smtpusername=?,smtppassword=?,mailaddress=? where username=?";
			PreparedStatement pStat = DButil.getInstance().getPreparedStatement(sql);
			pStat.setString(1, mailSetting.getVimapIp());
			pStat.setString(2, mailSetting.getVimapUserName());
			pStat.setString(3, mailSetting.getVimapUserPassword());
			pStat.setString(4, mailSetting.getVsmtpIp());
			pStat.setString(5, mailSetting.getVsmtpUserName());
			pStat.setString(6, mailSetting.getVsmtpUserPassword());
			pStat.setString(7, mailSetting.getVMailAddress());
			pStat.setString(8, userName);
			pStat.executeUpdate();
			DButil.getInstance().closePrepareStatement(pStat);
		}else{
			int Num = 0;
			
			if(MailSetting.DATABASETYPE.equals("oracle")) { 
				PreparedStatement pStatNum = DButil.getInstance().getPreparedStatement("select seq_mmailsetting.nextval from dual");
				ResultSet rs = pStatNum.executeQuery();
				while(rs.next()){
					Num = rs.getInt(1);
				}
				rs.close();
			}
			
			sql = "insert into t_mmailsetting values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStat = DButil.getInstance().getPreparedStatement(sql);
			pStat.setInt(1, Num);
			pStat.setString(2, userName);
			pStat.setString(3, mailSetting.getVimapIp());
			pStat.setString(4, mailSetting.getVimapUserName());
			pStat.setString(5, mailSetting.getVimapUserPassword());
			pStat.setString(6, mailSetting.getVsmtpIp());
			pStat.setString(7, mailSetting.getVsmtpUserName());
			pStat.setString(8, mailSetting.getVsmtpUserPassword());
			pStat.setString(9, mailSetting.getVMailAddress());
			pStat.executeUpdate();
			 
			DButil.getInstance().closePrepareStatement(pStat);
		}
		return mailSetting;
	}

	@Override
	public MailSetting getMailSetting(String userName) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_mmailsetting where username = ?");
		pStat.setString(1, userName);
		ResultSet rs = pStat.executeQuery();
		MailSetting ms = null;
		while(rs.next()){
			ms = new MailSetting(rs.getString("imapip"),rs.getString("imapusername"),rs.getString("imappassword"),rs.getString("smtpip"),rs.getString("smtpusername"),rs.getString("smtppassword"),rs.getString("mailaddress"));
		}
		return ms;
		
	}

	@Override
	public int hasSetting(String userName) throws SQLException {
		int count = 0;
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select count(*) from t_mmailsetting where username=?");
		pStat.setString(1, userName);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		return count;
	}

}
