package com.mail.util;


import com.mail.common.CommonInfo;

import java.sql.*;
/**
 * 
 * 
 *	数据库工具类，用到了单例模式
 */
public class DButil {
	private static DButil instance = null;
	private Connection conn = null;
	
	private DButil() throws SQLException {
		try {
			Class.forName(CommonInfo.DB_DRIVER_CLASSNAME);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(CommonInfo.DB_CONN_STRING);
		//new OracleDriver();
		//conn = DriverManager.getConnection(CommonInfo.ORACEL_DB_CONN_STRING,CommonInfo.ORACEL_DB_USERNAME,CommonInfo.ORACEL_DB_PASSWORD);
		conn.setAutoCommit(false);
	}
	
	public static DButil getInstance() throws SQLException{
		if(instance == null){
			instance = new DButil();
		}
		return instance;
	}
	
	public Connection getConnection(){
		return this.conn;
	}
	
	public Statement getStatement() throws SQLException{
		return conn.createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		return conn.prepareStatement(sql);
	}
	
	public void closePrepareStatement(PreparedStatement pStat) throws SQLException{
		pStat.close();
	}
	
	public void closeStatement(Statement Stat) throws SQLException{
		Stat.close();
	}
	
	public void closeConnection(Connection conn) throws SQLException{
		conn.close();
	}
	
	public void closeResultSet(ResultSet rs) throws SQLException{
		rs.close();
	}
	
	public void getCommit() throws SQLException{
		conn.commit();
	}
	
	public void getRollback() throws SQLException{
		conn.rollback();
	}
	
}




