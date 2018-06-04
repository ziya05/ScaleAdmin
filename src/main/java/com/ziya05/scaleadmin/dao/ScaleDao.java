package com.ziya05.scaleadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.UserBean;

public class ScaleDao implements IScaleDao {

	public int getPageCount(String userName, String date, String scaleName) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		userName = getColString(userName);
		date = getColString(date);
		scaleName = getColString(scaleName);
		
		System.out.println("---------userName: " + userName);
		System.out.println("---------date: " + date);
		System.out.println("---------scaleName: " + scaleName);
		
		String sql = "select count(1) from TesteeBase t, Scale s where t.scaleId = s.id "
				+ " and (case when ? is null then 1=1 else t.name like ? end)"
				+ " and (case when ? is null then 1=1 else t.testTime like ? end)"
				+ " and (case when ? is null then 1=1 else s.name like ? end)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userName);
		pstmt.setString(3, date);
		pstmt.setString(4, date);
		pstmt.setString(5, scaleName);
		pstmt.setString(6, scaleName);
	
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		rs.close();
		conn.close();
		
		return count;
	}

	public List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, 
			int pageIndex,
			int pageLength) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		userName = getColString(userName);
		date = getColString(date);
		scaleName = getColString(scaleName);
		
		int offset = (pageIndex - 1) * pageLength;
		
		System.out.println("offset: " + offset);
		System.out.println("pageIndex: " + pageIndex);
		System.out.println("pageLength: " + pageLength);

		String sql = "select t.id as id, s.id as scaleId, s.name as scaleName, t.name as userName, t.testTime from TesteeBase t, Scale s where t.scaleId = s.id "
				+ " and (case when ? is null then 1=1 else t.name like ? end)"
				+ " and (case when ? is null then 1=1 else t.testTime like ? end)"
				+ " and (case when ? is null then 1=1 else s.name like ? end)"
				+ " order by t.testTime desc, s.id, t.name"
				+ " limit ?,?";
		
		System.out.println(sql);
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userName);
		pstmt.setString(3, date);
		pstmt.setString(4, date);
		pstmt.setString(5, scaleName);
		pstmt.setString(6, scaleName);
		pstmt.setInt(7, offset);
		pstmt.setInt(8, pageLength);
		
		ResultSet rs = pstmt.executeQuery();
		List<TesteeBaseBean> lst = new ArrayList<TesteeBaseBean>();
		while(rs.next()){
			TesteeBaseBean bean = new TesteeBaseBean();
			bean.setId(rs.getInt("id"));
			bean.setId(rs.getInt("scaleId"));
			bean.setScaleName(rs.getString("scaleName"));
			bean.setUserName(rs.getString("userName"));
			bean.setTestTime(rs.getDate("testTime"));
			lst.add(bean);
		}
		
		rs.close();
		conn.close();
		return lst;
	}
	
	public UserBean getUserBean(String account, String password) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		String sql = "select id, account, password, name, type from User where account=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		
		UserBean userBean = null;
		if (rs.next()) {
			userBean = new UserBean();
			userBean.setId(rs.getInt("id"));
			userBean.setAccount(rs.getString("account"));
			userBean.setPassword(rs.getString("password"));
			userBean.setName(rs.getString("name"));
			userBean.setType(rs.getInt("type"));
		}
		
		return userBean;
	}

	private Connection getConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/scale?characterEncoding=utf8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(url, "scale", "scale-01");
		
		return conn;
	}
	
	private String getColString(String data) {
		if (!StringUtils.isBlank(data)) {
			data = "%" + data + "%";
		} else {
			data = null;
		}
		
		return data;
	}
}
