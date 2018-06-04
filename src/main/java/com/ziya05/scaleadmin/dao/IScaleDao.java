package com.ziya05.scaleadmin.dao;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.UserBean;

public interface IScaleDao {
	int getPageCount(String userName, String date, String scaleName) throws ClassNotFoundException, SQLException;
	
	List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, int pageIndex, int pageLength) throws ClassNotFoundException, SQLException;
	
	UserBean getUserBean(String account, String password) throws ClassNotFoundException, SQLException;
}
