package com.ziya05.scaleadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.ziya05.scaleadmin.beans.FactorScoreLevelBean;
import com.ziya05.scaleadmin.beans.InfoItemBean;
import com.ziya05.scaleadmin.beans.ResultAdviceBean;
import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.TesteeDataItemBean;
import com.ziya05.scaleadmin.beans.TesteePersonalInfoBean;
import com.ziya05.scaleadmin.beans.UserBean;

public class ScaleDao implements IScaleDao {
	private DataSource ds;
	
	public ScaleDao(DataSource ds) {
		this.ds = ds;
	}

	public int getPageCount(String userName, String date, String scaleName) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		userName = getColString(userName);
		date = getColString(date);
		scaleName = getColString(scaleName);
		
		String sql = "select count(1) from testeebase t, scale s where t.scaleId = s.id "
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
		
		String sql = "select t.id as id, s.id as scaleId, s.name as scaleName, t.name as userName, t.gender, t.age, t.testTime from testeebase t, scale s where t.scaleId = s.id "
				+ " and (case when ? is null then 1=1 else t.name like ? end)"
				+ " and (case when ? is null then 1=1 else t.testTime like ? end)"
				+ " and (case when ? is null then 1=1 else s.name like ? end)"
				+ " order by t.testTime desc, s.id, t.name"
				+ " limit ?,?";
		
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
			bean.setScaleId(rs.getInt("scaleId"));
			bean.setScaleName(rs.getString("scaleName"));
			bean.setUserName(rs.getString("userName"));
			bean.setGender(rs.getString("gender"));
			bean.setAge(rs.getDouble("age"));
			bean.setTestTime(rs.getTimestamp("testTime"));
			lst.add(bean);
		}
		
		rs.close();
		conn.close();
		return lst;
	}
	
	public UserBean getUserBean(String account, String password) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		String sql = "select id, account, password, name, type from user where account=? and password=?";
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
		
		rs.close();
		conn.close();
		
		return userBean;
	}
	
	public TesteeBaseBean GetTesteeBase(int id, int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		String sql = "select t.id as id, s.id as scaleId, s.name as scaleName, t.name as userName, t.gender, t.age, t.testTime from testeebase t, scale s where t.scaleId = s.id and t.id=? and s.id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();
		
		boolean b = rs.next();
		System.out.println("获取测试基本信息情况：" + b);
		
		TesteeBaseBean bean = new TesteeBaseBean();
		bean.setId(rs.getInt("id"));
		bean.setScaleId(rs.getInt("scaleId"));
		bean.setScaleName(rs.getString("scaleName"));
		bean.setUserName(rs.getString("userName"));
		bean.setGender(rs.getString("gender"));
		bean.setAge(rs.getDouble("age"));
		bean.setTestTime(rs.getTimestamp("testTime"));

		rs.close();
		conn.close();
		
		return bean;
	}
	
	public TesteePersonalInfoBean GetTesteePersonalInfo(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		
		String sql = "select name, title, content from testeepersonalinfo where baseId=? and scaleId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();
		TesteePersonalInfoBean bean = new TesteePersonalInfoBean();
		List<InfoItemBean> items = new ArrayList<InfoItemBean>();
		bean.setItems(items);
		while(rs.next()) {
			InfoItemBean item = new InfoItemBean();
			item.setName(rs.getString("name"));
			item.setTitle(rs.getString("title"));
			item.setContent(rs.getString("content"));
			items.add(item);
		}

		rs.close();
		conn.close();
		return bean;
	}
	
	public List<TesteeDataItemBean> GetTesteeDataItemList(int id, int scaleId)
			throws ClassNotFoundException, SQLException, IndexOutOfBoundsException {
		Connection conn = this.getConn();
		String sql = "select questionIds, optionSelected, scoreSelected from testeedata where baseId=? and scaleId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		
		String questionIds = this.trimLastSign(rs.getString("questionIds"));
		String optionSelected = this.trimLastSign(rs.getString("optionSelected"));
		String scoreSelected = this.trimLastSign(rs.getString("scoreSelected"));
		
		rs.close();
		conn.close();
		
	    String[] questionArr = questionIds.split(",");
	    String[] optionArr = optionSelected.split(",");
	    String[] scoreArr = scoreSelected.split(",");
	    
	    if (questionArr.length != optionArr.length 
	    		|| questionArr.length != scoreArr.length) {
	    	throw new IndexOutOfBoundsException("问题个数与选项个数或分数个数不同！");
	    }
		
		List<TesteeDataItemBean> lst = new ArrayList<TesteeDataItemBean>();
		for(int i = 0; i< questionArr.length; i++) {
			TesteeDataItemBean bean = new TesteeDataItemBean();
			bean.setQuestionId(Integer.parseInt(questionArr[i]));
			bean.setOptionId(optionArr[i]);
			bean.setScore(Integer.parseInt(scoreArr[i]));
			lst.add(bean);
		}
		
		return lst;
	}
	
	public String GetGroups(int id, int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		String sql = "select `groups` from resultbase where testeeBaseId=? and scaleId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();

		String groups = "";
		if(rs.next()) {
			groups = rs.getString("groups");
		}
		
		rs.close();
		conn.close();
		
		return this.trimLastSign(groups);
	}
	
	public List<ResultAdviceBean> GetResultAdviceList(int id, int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		String sql = "select name, score, levelId, description, advice from resultfactor where testeeBaseId=? and scaleId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();
		
		List<ResultAdviceBean> lst = new ArrayList<ResultAdviceBean>();
		while(rs.next()) {
			ResultAdviceBean bean = new ResultAdviceBean();
			bean.setFactorName(rs.getString("name"));
			bean.setScore(rs.getDouble("score"));
			bean.setLevel(rs.getInt("levelId"));
			bean.setDescription(rs.getString("description"));
			bean.setAdvice(rs.getString("advice"));
			lst.add(bean);
		}
		
		rs.close();
		conn.close();
		
		return lst;
	}
	
	public List<FactorScoreLevelBean> GetFactorScoreLevelList(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		Connection conn = this.getConn();
		String sql = "select name, score, levelId from resultfactor where testeeBaseId=? and scaleId=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.setInt(2, scaleId);
		ResultSet rs = pstmt.executeQuery();
		
		List<FactorScoreLevelBean> lst = new ArrayList<FactorScoreLevelBean>();
		while(rs.next()) {
			FactorScoreLevelBean bean = new FactorScoreLevelBean();
			bean.setName(rs.getString("name"));
			bean.setScore(rs.getDouble("score"));
			bean.setLevel(rs.getInt("levelId"));
			lst.add(bean);
		}
		
		rs.close();
		conn.close();
		
		return lst;
	}

	private Connection getConn() throws SQLException, ClassNotFoundException {
		return ds.getConnection();
	}
	
	private String getColString(String data) {
		if (!StringUtils.isBlank(data)) {
			data = "%" + data + "%";
		} else {
			data = null;
		}
		
		return data;
	}
	
	private String trimLastSign(String data) {
		return this.trimLastSign(data, ',');
	}
	
	private String trimLastSign(String data, char c) {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		
		char lastChar = data.charAt(data.length() - 1);
		if (lastChar == c) {
			return data.substring(0, data.length() - 1);
		}
		
		return data;
	}

}
