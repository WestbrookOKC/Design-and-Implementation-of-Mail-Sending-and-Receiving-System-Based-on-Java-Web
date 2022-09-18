package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailAddress;
import com.mail.bean.MailSetting;
import com.mail.dao.AddressDao;
import com.mail.util.DButil;

/**
 * 
 * 
 *	 实现AddressDao接口,对数据库进行一些操作
 */

public class AddressImpl implements AddressDao{
	
	@Override
	public boolean insertAddressInfo(String userName, String friendName,
			String friendMail) throws SQLException {
		int Nom = 0;
		
		if(MailSetting.DATABASETYPE.equals("oracle")) {
			PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select seq_maddress.nextval from dual");
			ResultSet rss = pStats.executeQuery();
			while(rss.next()){
				Nom = rss.getInt(1);
			}
			DButil.getInstance().closeResultSet(rss);
		}
		
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("insert into t_maddress values(?,?,?,?)");
		pStat.setInt(1, Nom);
		pStat.setString(2, userName);
		pStat.setString(3, friendName);
		pStat.setString(4, friendMail);
		pStat.executeUpdate();
		
		DButil.getInstance().closePrepareStatement(pStat);
		return true;
	}

	@Override
	public int isExist(String userName, String friendName) throws SQLException {
		int count = 0;
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select count(*) from t_maddress where username =? and friendname=?"); 
		pStat.setString(1, userName);
		pStat.setString(2, friendName);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			count = rs.getInt(1);
		}
		DButil.getInstance().closeResultSet(rs);
		return count;
	}

	@Override
	public Vector<MailAddress> getFriendInfo(String userName)
			throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_maddress where username=? order by friendname");
		pStat.setString(1, userName);
		ResultSet rs = pStat.executeQuery();
		Vector<MailAddress> malist = new Vector<MailAddress>();
		while(rs.next()){
			malist.add(new MailAddress(rs.getInt("id"),rs.getString("friendname"),rs.getString("friendmail")));
		}
		DButil.getInstance().closeResultSet(rs);
		return malist;
	}

	@Override
	public boolean deleteAddressInfo(String userName, String friendMail)
			throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("delete from t_maddress where username=? and friendmail=?");
		pStat.setString(1, userName);
		pStat.setString(2, friendMail);
		pStat.executeUpdate();
		return true;
	}

	@Override
	public boolean updateAddressInfo(String userName, String friendName,String friendMail)
			throws SQLException {
		PreparedStatement pStats = DButil.getInstance().getPreparedStatement("select id from t_maddress where userName=? and friendmail =?");
		pStats.setString(1, userName);
		pStats.setString(2, friendMail);
		ResultSet rss = pStats.executeQuery();
		rss.next();
		int id = rss.getInt("id");
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("update t_maddress set friendname=?,friendmail=? where id=?");
		pStat.setString(1, friendName);
		pStat.setString(2, friendMail);
		pStat.setInt(3, id);
		pStat.executeUpdate();
		return true;
	}
	
	

}
