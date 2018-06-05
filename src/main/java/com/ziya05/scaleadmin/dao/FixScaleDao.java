package com.ziya05.scaleadmin.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.TesteeDataItemBean;
import com.ziya05.scaleadmin.beans.TesteePersonalInfoBean;
import com.ziya05.scaleadmin.beans.UserBean;

public class FixScaleDao implements IScaleDao {

	public int getPageCount(String userName, String date, String scaleName) {
		return 20;
	}
	
	public List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, int pageIndex, int pageLength) {
		List<TesteeBaseBean> lst = new ArrayList<TesteeBaseBean>();

		lst.add(new TesteeBaseBean(1, 1, "这是哪个量表", "哦", "女", new Date()));
		lst.add(new TesteeBaseBean(2, 1, "这是哪个量表", "啦啦", "男", new Date()));
		lst.add(new TesteeBaseBean(3, 2, "这是为啥呢？", "啦啦", "男", new Date()));
		lst.add(new TesteeBaseBean(4, 2, "这是为啥呢？", "啦啦", "男", new Date()));
		lst.add(new TesteeBaseBean(5, 2, "这是为啥呢？", "嘿嘿", "女", new Date()));
		lst.add(new TesteeBaseBean(6, 2, "这是为啥呢？", "嘿嘿", "女", new Date()));
		
		return lst;
	}

	public UserBean getUserBean(String account, String password) {
		return new UserBean(1, "admin", "123456", "管理员", 1);
	}

	public TesteeBaseBean GetTesteeBase(int id, int scaleId) throws ClassNotFoundException, SQLException {
		return new TesteeBaseBean(3, 2, "这是为啥呢？", "啦啦", "男", new Date());
	}

	public TesteePersonalInfoBean GetTesteePersonalInfo(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TesteeDataItemBean> GetTesteeDataItemList(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String GetGroups(int id, int scaleId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
