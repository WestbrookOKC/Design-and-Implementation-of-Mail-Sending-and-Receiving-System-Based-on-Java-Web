package com.mail.modeler;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import com.mail.bean.MailUser;
import com.mail.common.CommonInfo;
import com.mail.dao.UserDao;
import com.mail.impl.UserImpl;
import com.mail.util.DButil;

public class MMailUser {
	
	@SuppressWarnings("unchecked")
	public boolean registerNewUser(HttpSession mySession,MailUser user){
		UserDao userImpl = new UserImpl();
		//设置用户信息
		@SuppressWarnings({ "rawtypes" })
		Hashtable myValues =(Hashtable)mySession.getAttribute(CommonInfo.VIEWID_REGISTER);
		myValues.put("username", user.getVuserName());
		myValues.put("realname", user.getVrealName());
		
		try {
			int UserImp = userImpl.isExist(user);
			if(UserImp>0){
				mySession.setAttribute("errMsg", "用户名已将存在");
				return false;
			}
			userImpl.register(user);
			DButil.getInstance().getCommit();
			mySession.setAttribute("username", user.getVuserName());
            mySession.setAttribute("realname",user.getVrealName());
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "数据库错误，注册失败！");
			try {
				DButil.getInstance().getRollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean checkUserInfo(HttpSession mySession,String userName,String password){
		
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_LOGIN);
		myValues.put("username", userName);
		UserDao ud = new UserImpl();
		MailUser mu = null;
		try {
			int errorInfo = ud.checkUser(new MailUser(userName,password,null,null,null,null));
			mu = ud.selectUserByUserNameAndPassword(userName, password);
			if(errorInfo == CommonInfo.USER_NAME_ERROR){
				mySession.setAttribute("errMsg", "用户名不存在！");
				return false;
			}
			if(errorInfo == CommonInfo.PASSWORD_ERROR){
				mySession.setAttribute("errMsg", "密码错误");
				return false;
			}
			if(errorInfo == CommonInfo.THE_USER_PAKING){
				mySession.setAttribute("errMsg", "你的账号已被管理员锁定！有问题给管理员留言！");
				return false;
			}
			mySession.setAttribute("realname", mu.getVrealName());
			mySession.setAttribute("power", mu.getPower());
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "数据库异常");
			return false;
		}
		mySession.setAttribute("username", userName);
		return true;		
	}
	//修改密码
	public boolean changePassword(HttpSession mySession,String userName,String inputOldPassword,String newPassword){
		UserDao ud = new UserImpl();
		try {
			String oldPassword = ud.getOldPassword(userName);	//获取旧密码
			if(oldPassword.equals(inputOldPassword)){			//判断输入的密码和旧密码是否一致
				boolean b = ud.changePassword(userName, newPassword); //输入的旧密码和新密码一致就更改密码
				if(b){
					DButil.getInstance().getCommit();				//更改成功提交事务
				}else{
					DButil.getInstance().getRollback();
					mySession.setAttribute("errMsg", "密码修改失败！");
					return false;
				}
			}else{
				mySession.setAttribute("errMsg", "旧密码输入错误！");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "密码修改失败！");
			return false;
		}
		return true;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getQuestion(HttpSession mySession,String userName_Mobile){
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_FINDPASSWORD);
		UserDao ud = new UserImpl();
		int type = 0;
		String sUserName_Mobile = null;
		try{
			sUserName_Mobile = Integer.parseInt(userName_Mobile)+"";
			type = CommonInfo.FIND_BY_MOBILE;
		}catch(Exception e){
			sUserName_Mobile = userName_Mobile;
			type = CommonInfo.FIND_BY_USERNAME;
		}
		try {
			String question = ud.findMailUserPasswordQuestion(type, sUserName_Mobile);
			myValues.put("question", question);
			mySession.setAttribute("question", question);
			mySession.setAttribute("UserName_Mobile", sUserName_Mobile);
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取问题失败！请检查输入的用户名或手机是否正确！");
			return false;
		}
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getPassword(HttpSession mySession,String answer,String userName_Mobile){
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_FINDPASSWORD);
		UserDao ud = new UserImpl();
		try {
			userName_Mobile = (String)mySession.getAttribute("UserName_Mobile");
			String password = ud.findPasswordByAnwer(answer,userName_Mobile);
			myValues.put("password", password);
		} catch (SQLException e) {
			mySession.setAttribute("errMsg", "获密码失败！请检查输入的答案是否正确！");
			return false;
		}
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean getAllMailUserInfo(HttpSession mySession){
		UserDao ud = new UserImpl();
		Vector<MailUser> muList = null;
		Hashtable myValues = (Hashtable)mySession.getAttribute(CommonInfo.VIEWID_USERLIST);
		try {
			muList = ud.getMailUserInfo();
			myValues.put("users", muList);
			} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "获取用户信息失败");
			return false;
		}
		return true;
	}
	
	
	public boolean pakingUserByUserName(HttpSession mySession,String userName){
		UserDao ud = new UserImpl();
		try {
			if(userName!=null&&userName.equals(mySession.getAttribute("username"))){
				mySession.setAttribute("errMsg", "管理员，您好！您不能锁定自己的账户！");
				return false;
			}
			boolean b = ud.isPakingMailUserByUserName(CommonInfo.IS_PAKING,userName);
			if(b){
				DButil.getInstance().getCommit();
				mySession.setAttribute("errMsg", "账户"+userName+"锁定成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DButil.getInstance().getRollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			mySession.setAttribute("errMsg", "账户"+userName+"锁定失败！");
			return false;
		}
		return true;
	}
	
	public boolean removePakingUserByUserName(HttpSession mySession,String userName){
		UserDao ud = new UserImpl();
		try {
			boolean b = ud.isPakingMailUserByUserName(CommonInfo.NOT_PAKING, userName);
			if(b){
				DButil.getInstance().getCommit();
				mySession.setAttribute("errMsg", "账户"+userName+"解锁成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mySession.setAttribute("errMsg", "账户"+userName+"解锁失败");
			return false;
		}
		return true;
	}
}