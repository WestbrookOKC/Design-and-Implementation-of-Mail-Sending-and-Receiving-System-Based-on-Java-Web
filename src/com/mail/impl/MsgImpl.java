package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailMsg;
import com.mail.bean.MailSetting;
import com.mail.dao.MsgDao;
import com.mail.util.DButil;

public class MsgImpl implements MsgDao {

	@Override
	public boolean leaveMessage(MailMsg msg) throws SQLException {
		int Nom = 0;
		
		if(MailSetting.DATABASETYPE.equals("oracle")) { 
			PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select seq_mmsg.nextval from dual");
			ResultSet rss = pStats.executeQuery();
			rss.next();
			Nom = rss.getInt(1);
		}
		
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("insert into t_mmsg values(?,?,?,?)");
		pStat.setInt(1, Nom);
		pStat.setString(2, msg.getVuserName());
		pStat.setString(3, msg.getVmsgContent());
		pStat.setString(4, msg.getVmsgDate());
		pStat.executeQuery();
		return true;
	}

	@Override
	public Vector<MailMsg> getMsgInfo(String userName) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_mmsg");
		ResultSet rs = pStat.executeQuery();
		MailMsg msg = null;
		Vector<MailMsg> msgList = new Vector<MailMsg>();
		while(rs.next()){
			msg =new MailMsg();
			msg.setNid(rs.getInt("id"));
			msg.setVuserName("username");
			msg.setVmsgContent("msgContent");
			msg.setVmsgDate("msgdate");
			msgList.add(msg);
			
		}
		return msgList;
	}

}
