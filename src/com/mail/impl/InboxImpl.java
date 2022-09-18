package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailInbox;
import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.dao.InboxDao;
import com.mail.util.DButil;

public class InboxImpl implements InboxDao{

	@Override
	public boolean insertMailToDB(MailInbox inbox) throws SQLException {
		int Nom = 0;
		
		if(MailSetting.DATABASETYPE.equals("oracle")) {
			PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select seq_minbox.nextval from dual");
			ResultSet rsN = pStats.executeQuery();
			while(rsN.next()){
				Nom = rsN.getInt(1);
			}
		}
		
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("insert into t_minbox values(?,?,?,?,?,?,?,?,?)");
		pStat.setInt(1, Nom);
		pStat.setString(2, inbox.getVuserName());
		pStat.setString(3, inbox.getVseader());
		pStat.setString(4, inbox.getVreceiver());
		pStat.setString(5, inbox.getDsendTime());
		pStat.setString(6, inbox.getVsubject());
		pStat.setString(7, inbox.getVcontent());
		pStat.setInt(8, CommonInfo.NOT_READFLG);
		pStat.setInt(9, CommonInfo.NOT_HISTORY);
		//pStat.setString(10,inbox.getvFile());
		pStat.executeUpdate();
		return true;
	}

	@Override
	public Vector<MailInbox> selectByUserName(String userName) throws SQLException {
		Vector<MailInbox> inboxs = new Vector<MailInbox>();
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_minbox where username = ? and ishistory=? order by sendtime desc");
		pStat.setString(1, userName);
		pStat.setInt(2, CommonInfo.NOT_HISTORY);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			inboxs.add(new MailInbox(rs.getInt("id"),rs.getString("sender"),rs.getString("RECEIVER"),rs.getString("sendtime"),rs.getString("subject"),rs.getString("content"),rs.getInt("readFlg")));
		}
		return inboxs;
	}

	@Override
	public boolean setInboxMailIsRead(String userName, String sender,
			String sendTime) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("update t_minbox set readflg= ? where username=? and sender =? and sendtime = ?");
		pStat.setInt(1, CommonInfo.IS_READFLG);
		pStat.setString(2, userName);
		pStat.setString(3, sender);
		pStat.setString(4, sendTime);
		pStat.executeUpdate();
		DButil.getInstance().closePrepareStatement(pStat);
		return true;
	}

	@Override
	public boolean setInboxMailTohistory(String userName, String sender, String sendTime)
			throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("update t_minbox set ishistory=? where username=? and sender =? and sendTime = ?");
		pStat.setInt(1, CommonInfo.IS_HISTORY);
		pStat.setString(2, userName);
		pStat.setString(3, sender);
		pStat.setString(4, sendTime);
		pStat.executeUpdate();
		return true;
	}

	@Override
	public Vector<MailInbox> selectIshistoryByUserName(String userName)
			throws SQLException {
		Vector<MailInbox> mailList = new Vector<MailInbox>();
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_minbox where username=? and ishistory=? order by sendtime desc");
		pStat.setString(1, userName);
		pStat.setInt(2, CommonInfo.IS_HISTORY);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			mailList.add(new MailInbox(rs.getInt("id"),rs.getString("sender"),rs.getString("RECEIVER"),rs.getString("sendtime"),rs.getString("subject"),rs.getString("content"),rs.getInt("readFlg")));
		}
		return mailList;
	}

	
	/*
	 * 判断收件箱中是否已经添加了该邮件
	 */
	@Override
	public boolean checkSubjectExist(String userName,String subject) throws SQLException { 
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_minbox where username=? and subject=?");
		pStat.setString(1, userName);
		pStat.setString(2, subject);
		ResultSet rs = pStat.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean deleteInboxMail(String userName, String sender,
			String sendTime, String subject) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("delete from t_minbox where username=? and sender=? and sendtime=? and subject=?");
		pStat.setString(1, userName);
		pStat.setString(2, sender);
		pStat.setString(3, sendTime);
		pStat.setString(4, subject);
		pStat.executeUpdate();
		return true;
	}

}
