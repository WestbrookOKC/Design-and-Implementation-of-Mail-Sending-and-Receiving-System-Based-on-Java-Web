package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailContent;
import com.mail.bean.MailSendBox;
import com.mail.bean.MailSetting;
import com.mail.common.CommonInfo;
import com.mail.dao.SendBoxDao;
import com.mail.util.DButil;

public class SendBoxImpl implements SendBoxDao{

	@Override
	public boolean setSendMailIshistory(String userName, String saveTime)
			throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("update t_msendbox set ishistory=? t_msendbox where username=? and savetime=?");
		pStat.setInt(1, CommonInfo.IS_HISTORY);
		pStat.setString(2, userName);
		pStat.setString(3, saveTime);
		pStat.executeUpdate();
		return true;
	}

	@Override
	public boolean saveMail(String userName, MailContent mail)
			throws SQLException {
		int Nom = 0;
		
		if(MailSetting.DATABASETYPE.equals("oracle")) {
			PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select seq_msendbox.nextval from dual");
			ResultSet rss = pStats.executeQuery();
			while(rss.next()){
				Nom = rss.getInt(1);
			}
		}
		
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("insert into t_msendbox values(?,?,?,?,?,?,?,?)");
		pStat.setInt(1, Nom);
		pStat.setString(2, userName);
		pStat.setString(3, mail.getReseiver());
		pStat.setString(4, mail.getSubject());
		pStat.setString(5, mail.getContent());
		pStat.setString(6, mail.getSendTime());
		pStat.setInt(7, CommonInfo.NOT_HISTORY);
		pStat.setInt(8, CommonInfo.NOT_SEND);
		pStat.executeUpdate();
		return true;
	}

	@Override
	public Vector<MailSendBox> selectMailByUserName(String userName) throws SQLException {
		Vector<MailSendBox> mslist = new Vector<MailSendBox>();
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_msendbox where username=? and ishistory=? and issend=? order by savetime desc");
		pStat.setString(1, userName);
		pStat.setInt(2, CommonInfo.NOT_HISTORY);
		pStat.setInt(3, CommonInfo.NOT_SEND);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			mslist.add(new MailSendBox(rs.getInt("id"),rs.getString("receiver"),rs.getString("subject"),rs.getString("content"),rs.getString("savetime")));
		}
		
		return mslist;
	}

	@Override
	public boolean setHasSendMail(String userName, MailContent mail)
			throws SQLException {
		 
		PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select count(*) from t_msendbox where username=? and savetime=?");
		pStats.setString(1, userName);
		pStats.setString(2, mail.getSendTime());
		ResultSet rs = pStats.executeQuery();
		rs.next();
		PreparedStatement pStat = null;
		if(rs.getInt(1)>0){
			pStat = DButil.getInstance().getPreparedStatement("update t_msendbox set issend=? where username=? and savetime=?");
			pStat.setInt(1, CommonInfo.IS_SEND);
			pStat.setString(2, userName);
			pStat.setString(3, mail.getSendTime());
			 
		}else{
			int Nom = 0;
			if(MailSetting.DATABASETYPE.equals("oracle")) {
				PreparedStatement pStatss = DButil.getInstance().getPreparedStatement("select seq_msendbox.nextval from dual");
				ResultSet rss = pStatss.executeQuery();
				while(rss.next()){
					Nom = rss.getInt(1);
				}
			}
			pStat = DButil.getInstance().getPreparedStatement("insert into t_msendbox values(?,?,?,?,?,?,?,?)");
			pStat.setInt(1, Nom);
			pStat.setString(2, userName);
			pStat.setString(3, mail.getReseiver());
			pStat.setString(4, mail.getSubject());
			pStat.setString(5, mail.getContent());
			pStat.setString(6, mail.getSendTime());
			pStat.setInt(7, CommonInfo.NOT_HISTORY);
			pStat.setInt(8, CommonInfo.IS_SEND);
			 
		}
		pStat.executeUpdate();
		return true;
	}

	@Override
	public Vector<MailSendBox> selectHasSendMailByUserName(String userName)
			throws SQLException {
		Vector<MailSendBox> msList = new Vector<MailSendBox>();
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_msendbox where userName=? and issend=?");
		pStat.setString(1, userName);
		pStat.setInt(2, CommonInfo.IS_SEND);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			msList.add(new MailSendBox(rs.getInt("id"),rs.getString("receiver"),rs.getString("subject"),rs.getString("content"),rs.getString("savetime")));
		}
		return msList;
	}

	@Override
	public Vector<MailSendBox> selectIshistoryByUserName(String userName)
			throws SQLException {
		Vector<MailSendBox> msList = new Vector<MailSendBox>();
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_msendbox where username=? and ishistory=?");
		pStat.setString(1, userName);
		pStat.setInt(2, CommonInfo.IS_HISTORY);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			msList.add(new MailSendBox(rs.getInt("id"),rs.getString("receiver"),rs.getString("subject"),rs.getString("content"),rs.getString("savetime")));
		}
		return msList;
	}

	@Override
	public boolean deleteSendMail(String userName, String saveTime)
			throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("delete from t_msendbox where username=? and savetime=?");
		pStat.setString(1, userName);
		pStat.setString(2, saveTime);
		pStat.executeUpdate();
		return true;
	}

}
