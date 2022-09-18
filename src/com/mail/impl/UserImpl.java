package com.mail.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mail.bean.MailSetting;
import com.mail.bean.MailUser;
import com.mail.common.CommonInfo;
import com.mail.dao.UserDao;
import com.mail.util.DButil;

public class UserImpl implements UserDao{

	@Override
	public MailUser register(MailUser user) throws SQLException {
		
		int Nid = 0; 

		if(MailSetting.DATABASETYPE.equals("oracle")) {
			PreparedStatement pStatement = DButil.getInstance().getPreparedStatement("select seq_muser.nextval from dual");
			ResultSet rSet = pStatement.executeQuery();
			while(rSet.next())
			{
				Nid=rSet.getInt(1);
			} 
		}
		
		try  {
			PreparedStatement pStat = DButil.getInstance().getPreparedStatement("insert into t_muser values(?,?,?,?,?,?,?,?,?,?,?)");
			pStat.setInt(1, Nid);
			pStat.setString(2, user.getVuserName());
			pStat.setString(3, user.getVpassWord());
			pStat.setString(4, user.getVrealName());
			pStat.setString(5, user.getDbirthday());
			pStat.setString(6, user.getVsex());
			pStat.setString(7, user.getNmobile());
			pStat.setInt(8, CommonInfo.NOT_SUPER);
			pStat.setInt(9, CommonInfo.NOT_HISTORY);
			pStat.setString(10, user.getVquestion());
			pStat.setString(11, user.getVanswer()); 
			//ResultSet rs = pStat.executeQuery(); 
			pStat.executeUpdate(); 
			MailUser returnUser = null;
			returnUser = new MailUser(Nid,user.getVuserName(),user.getVpassWord(),user.getVrealName(),user.getDbirthday(),user.getVsex(),user.getNmobile());			
			//DButil.getInstance().closeResultSet(rs);
			return returnUser;
			
		} catch(Exception ex) {
			System.out.println(ex.toString());
			
			return null;
		}
	
		
		
	}

	@Override
	public int checkUser(MailUser user) throws SQLException {
		int errorInfo = 0;
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_muser");
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			if(rs.getString("username").equals(user.getVuserName())){
				if(rs.getInt("ishistory")==CommonInfo.IS_HISTORY){
					errorInfo = CommonInfo.THE_USER_PAKING;
					break;
				}
				if(rs.getString("password").equals(user.getVpassWord())){
					errorInfo = 0;
					break;					
				}else{
					errorInfo = CommonInfo.PASSWORD_ERROR;
					break;
				}
			}else{
				errorInfo = CommonInfo.USER_NAME_ERROR;
			}
			
		}
		rs.close();
		return errorInfo;
	}

	@Override
	public MailUser selectUserByUserNameAndPassword(String userName, String password)
			throws SQLException {
		MailUser user = null;
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_muser where username=? and password=? and ishistory=?");
		pStat.setString(1, userName);
		pStat.setString(2, password);
		pStat.setInt(3, CommonInfo.NOT_HISTORY);
		ResultSet rs = pStat.executeQuery();
		while(rs.next()){
			user = new MailUser();
			user.setVrealName(rs.getString("realname"));
			user.setDbirthday("birthday");
			user.setNmobile(rs.getString("mobile"));
			user.setVsex(rs.getString("sex"));
			user.setPower(rs.getInt("issuper")+"");
		}
		DButil.getInstance().closeResultSet(rs);
		return user;
	}

	@Override
	public int isExist(MailUser user) throws SQLException {
		
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select count(*) from t_muser where username = ?");
		pStat.setString(1, user.getVuserName());
		ResultSet rs = pStat.executeQuery();
		int count = 0;
		while(rs.next()){
			count = rs.getInt(1);			
		}
		DButil.getInstance().closeResultSet(rs);
		return count;
	}

	@Override
	public boolean changePassword(String userName,String newPassword) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("update t_muser set password=? where username =?");
		pStat.setString(1, newPassword);
		pStat.setString(2, userName);
		pStat.executeUpdate();
		DButil.getInstance().closePrepareStatement(pStat);
		return true;
	}

	@Override
	public String getOldPassword(String userName) throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select password from t_muser where username = ?");
		pStat.setString(1, userName);
		ResultSet rs = pStat.executeQuery();
		rs.next();
		String password = rs.getString("password");
		DButil.getInstance().closeResultSet(rs);
		return password;
	}
	
	@Override
	public Vector<MailUser> getMailUserInfo() throws SQLException {
		PreparedStatement pStat = DButil.getInstance().getPreparedStatement("select * from t_muser order by id");
		ResultSet rs = pStat.executeQuery();
		Vector<MailUser> muList = new Vector<MailUser>();
		MailUser mu = null;
		while(rs.next()){
			mu = new MailUser();
			mu.setVuserName(rs.getString("username"));
			mu.setVpassWord(rs.getString("password"));
			mu.setVrealName( rs.getString("realname"));
			mu.setVsex(rs.getString("sex"));
			mu.setVquestion(rs.getString("question"));
			mu.setIspacking(rs.getInt("ishistory"));
			mu.setDbirthday("brithday");
			mu.setVanswer(rs.getString("answer"));
			mu.setNmobile(rs.getString("mobile"));
			muList.add(mu);
		}
		return muList;
	}

	@Override
	public boolean isPakingMailUserByUserName(int ispaking,String userName)
			throws SQLException {
		PreparedStatement pStat = null;
		pStat = DButil.getInstance().getPreparedStatement("update t_muser set ishistory =? where username=?");
		if(ispaking==CommonInfo.IS_PAKING){
			pStat.setInt(1, CommonInfo.IS_HISTORY);
		}else if(ispaking==CommonInfo.NOT_PAKING){
			pStat.setInt(1, CommonInfo.NOT_HISTORY);
		}
		pStat.setString(2, userName);
		pStat.executeUpdate();
		return true;
	}

	@Override
	public MailUser findMailUserByUserName(MailUser user) throws SQLException {
		
		return null;
	}

	@Override
	public String findMailUserPasswordQuestion(int type,String userName)
			throws SQLException {
		PreparedStatement pStat = null;
		if(type==CommonInfo.FIND_BY_USERNAME){
			pStat = DButil.getInstance().getPreparedStatement("select * from t_muser where username=?");
			pStat.setString(1, userName);
		}else if(type==CommonInfo.FIND_BY_MOBILE){
			pStat = DButil.getInstance().getPreparedStatement("select * from t_muser where mobile=?");
			pStat.setString(1, userName);
		}
		ResultSet rs = pStat.executeQuery();
		rs.next();
		String question = rs.getString("question");
		DButil.getInstance().closeResultSet(rs);
		return question;
	}

	@Override
	public String findPasswordByAnwer(String answer,String userName) throws SQLException {
		PreparedStatement pStat = null;
		pStat = DButil.getInstance().getPreparedStatement("select * from t_muser where answer=? and userName=?");
		pStat.setString(1, answer);
		pStat.setString(2, userName);
		ResultSet rs = pStat.executeQuery();
		rs.next();
		String password = rs.getString("password");
		DButil.getInstance().closeResultSet(rs);
		return password;
	}

	
}
