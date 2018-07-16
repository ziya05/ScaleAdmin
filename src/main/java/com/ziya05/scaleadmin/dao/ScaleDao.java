package com.ziya05.scaleadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.ziya05.scaleadmin.beans.BaseScaleBean;
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
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count;
		
		try {
		conn = this.getConn();
		
		userName = getColString(userName);
		date = getColString(date);
		scaleName = getColString(scaleName);
		
		String sql = "select count(1) from testeebase t, scale s where t.scaleId = s.id "
				+ " and (case when ? is null then 1=1 else t.name like ? end)"
				+ " and (case when ? is null then 1=1 else t.testTime like ? end)"
				+ " and (case when ? is null then 1=1 else s.name like ? end)";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userName);
		pstmt.setString(3, date);
		pstmt.setString(4, date);
		pstmt.setString(5, scaleName);
		pstmt.setString(6, scaleName);
	
		rs = pstmt.executeQuery();
		rs.next();
		count = rs.getInt(1);
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return count;
	}
	
	public List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, 
			int pageIndex,
			int pageLength) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<TesteeBaseBean> lst = new ArrayList<TesteeBaseBean>();
		
		try {
		conn = this.getConn();
		
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
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userName);
		pstmt.setString(2, userName);
		pstmt.setString(3, date);
		pstmt.setString(4, date);
		pstmt.setString(5, scaleName);
		pstmt.setString(6, scaleName);
		pstmt.setInt(7, offset);
		pstmt.setInt(8, pageLength);
		
		rs = pstmt.executeQuery();
		
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
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return lst;
	}
	
	public UserBean getUserBean(String account, String password) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserBean userBean = null;
		
		try {
			conn = this.getConn();
			
			String sql = "select id, account, password, name, type from user where account=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getInt("id"));
				userBean.setAccount(rs.getString("account"));
				userBean.setPassword(rs.getString("password"));
				userBean.setName(rs.getString("name"));
				userBean.setType(rs.getInt("type"));
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return userBean;
	}
	
	public BaseScaleBean getBaseScale(int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BaseScaleBean baseScale = null;
		
		try {
			conn = this.getConn();
			
			String sql = "select scaleNumber, name, description, questionCount, chartType from scale where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scaleId);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				baseScale = new BaseScaleBean();
				baseScale.setScaleNumber(rs.getString("scaleNumber"));
				baseScale.setName(rs.getString("name"));
				baseScale.setDescription(rs.getString("description"));
				baseScale.setQuestionCount(rs.getInt("questionCount"));
				baseScale.setChartType(rs.getInt("chartType"));
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return baseScale;
	}
	
	public TesteeBaseBean GetTesteeBase(int id, int scaleId) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		TesteeBaseBean bean = new TesteeBaseBean();
		
		try {
			conn = this.getConn();
			
			String sql = "select t.id as id, s.id as scaleId, s.name as scaleName, t.name as userName, t.gender, t.age, t.testTime from testeebase t, scale s where t.scaleId = s.id and t.id=? and s.id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			
			boolean b = rs.next();
			
			bean.setId(rs.getInt("id"));
			bean.setScaleId(rs.getInt("scaleId"));
			bean.setScaleName(rs.getString("scaleName"));
			bean.setUserName(rs.getString("userName"));
			bean.setGender(rs.getString("gender"));
			bean.setAge(rs.getDouble("age"));
			bean.setTestTime(rs.getTimestamp("testTime"));

		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return bean;
	}
	
	public TesteePersonalInfoBean GetTesteePersonalInfo(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		TesteePersonalInfoBean bean = new TesteePersonalInfoBean();
		
		try {	
			conn = this.getConn();
			
			String sql = "select name, title, content from testeepersonalinfo where baseId=? and scaleId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			
			List<InfoItemBean> items = new ArrayList<InfoItemBean>();
			bean.setItems(items);
			while(rs.next()) {
				InfoItemBean item = new InfoItemBean();
				item.setName(rs.getString("name"));
				item.setTitle(rs.getString("title"));
				item.setContent(rs.getString("content"));
				items.add(item);
			}

		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return bean;
	}
	
	public List<TesteeDataItemBean> GetTesteeDataItemList(int id, int scaleId)
			throws ClassNotFoundException, SQLException, IndexOutOfBoundsException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<TesteeDataItemBean> lst = new ArrayList<TesteeDataItemBean>();
		
		try {
			conn = this.getConn();
			String sql = "select questionIds, optionSelected, scoreSelected from testeedata where baseId=? and scaleId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			rs.next();
			
			String questionIds = this.trimLastSign(rs.getString("questionIds"));
			String optionSelected = this.trimLastSign(rs.getString("optionSelected"));
			String scoreSelected = this.trimLastSign(rs.getString("scoreSelected"));
			
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			
			sql = "select questionId, text from testeedatatext where baseId=? and scaleId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			
			Map<Integer, String> textMap = new HashMap<Integer, String>();
			while(rs.next()) {
				textMap.put(rs.getInt("questionId"), rs.getString("text"));
			}
			
		    String[] questionArr = questionIds.split(",");
		    String[] optionArr = optionSelected.split(",");
		    String[] scoreArr = scoreSelected.split(",");
		    
		    if (questionArr.length != optionArr.length 
		    		|| questionArr.length != scoreArr.length) {
		    	throw new IndexOutOfBoundsException("问题个数与选项个数或分数个数不同！");
		    }

			for(int i = 0; i< questionArr.length; i++) {
				TesteeDataItemBean bean = new TesteeDataItemBean();
				bean.setQuestionId(Integer.parseInt(questionArr[i]));
				bean.setOptionId(optionArr[i]);
				bean.setScore(Integer.parseInt(scoreArr[i]));
				
				if (textMap.containsKey(bean.getQuestionId())) {
					bean.setText(textMap.get(bean.getQuestionId()));
				}
				
				lst.add(bean);
			}														
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return lst;
	}
	
	public String GetGroups(int id, int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String groups = "";
		
		try {
			conn = this.getConn();
			String sql = "select `groups` from resultbase where testeeBaseId=? and scaleId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				groups = rs.getString("groups");
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return this.trimLastSign(groups);
	}
	
	public List<ResultAdviceBean> GetResultAdviceList(int id, int scaleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ResultAdviceBean> lst = new ArrayList<ResultAdviceBean>();
		
		try {
			conn = this.getConn();
			String sql = "select r.factorId, r.name, r.score, r.levelId, l.description, l.advice "
					+ "from resultfactor r, level l where r.testeeBaseId = ? and r.scaleId = ? "
					+ "and r.levelId > 0 and r.scaleId = l.scaleId "
					+ "and r.factorId = l.factorId and r.levelId = l.levelId order by r.factorId";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ResultAdviceBean bean = new ResultAdviceBean();
				bean.setFactorId(rs.getInt("factorId"));
				bean.setFactorName(rs.getString("name"));
				bean.setScore(rs.getDouble("score"));
				bean.setLevel(rs.getInt("levelId"));
				bean.setDescription(rs.getString("description"));
				bean.setAdvice(rs.getString("advice"));
				lst.add(bean);
			}
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return lst;
	}
	
	public List<FactorScoreLevelBean> GetFactorScoreLevelList(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		String sql = "select factorId, name, score, levelId from resultfactor where testeeBaseId=? and scaleId=? order by factorId";
		return this.GetFactorScoreLevelList(id, scaleId, sql);
	}
	
	public List<FactorScoreLevelBean> GetFactorScoreLevelListForChart(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		String sql = "select r.factorId, r.name, r.score, r.levelId from resultfactor r, factor f  where r.testeeBaseId=? and r.scaleId=? and r.scaleId = f.scaleId and r.factorId = f.factorId and f.inChart = 1 order by r.factorId";
		return this.GetFactorScoreLevelList(id, scaleId, sql);
	}
	
	private List<FactorScoreLevelBean> GetFactorScoreLevelList(int id, int scaleId, String sql) 
			throws ClassNotFoundException, SQLException{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<FactorScoreLevelBean> lst = new ArrayList<FactorScoreLevelBean>();
		
		try {
			conn = this.getConn();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setInt(2, scaleId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FactorScoreLevelBean bean = new FactorScoreLevelBean();
				bean.setFactorId(rs.getInt("factorId"));
				bean.setName(rs.getString("name"));
				bean.setScore(rs.getDouble("score"));
				bean.setLevel(rs.getInt("levelId"));
				lst.add(bean);
			}
		
		} finally {
			this.closeResultSet(rs);
			this.closeStatement(pstmt);
			this.closeConn(conn);
		}
		
		return lst;
	}

	private Connection getConn() throws SQLException, ClassNotFoundException {
		return ds.getConnection();
	}
	
	private void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null && !rs.isClosed()) {
			rs.close();
		}
	}
	
	private void closeStatement(Statement stmt) throws SQLException {
		if (stmt != null && !stmt.isClosed()) {
			stmt.close();
		}
	}
	
	private void closeConn(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed() ) {
			conn.close();
		}
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
