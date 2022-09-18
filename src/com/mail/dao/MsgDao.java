package com.mail.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailMsg;

public interface MsgDao {
	public boolean leaveMessage(MailMsg msg) throws SQLException;
	
	public Vector<MailMsg> getMsgInfo(String userName) throws SQLException;
}
