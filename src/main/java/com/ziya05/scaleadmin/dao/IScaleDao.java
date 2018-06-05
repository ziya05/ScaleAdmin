package com.ziya05.scaleadmin.dao;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.TesteeDataItemBean;
import com.ziya05.scaleadmin.beans.TesteePersonalInfoBean;
import com.ziya05.scaleadmin.beans.UserBean;

public interface IScaleDao {
	int getPageCount(String userName, String date, String scaleName) throws ClassNotFoundException, SQLException;
	
	List<TesteeBaseBean> getTesteeBaseBeanList(String userName, String date, String scaleName, int pageIndex, int pageLength) throws ClassNotFoundException, SQLException;
	
	UserBean getUserBean(String account, String password) throws ClassNotFoundException, SQLException;

	TesteeBaseBean GetTesteeBase(int id, int scaleId) throws ClassNotFoundException, SQLException;

	TesteePersonalInfoBean GetTesteePersonalInfo(int id, int scaleId) throws ClassNotFoundException, SQLException;
	
	List<TesteeDataItemBean> GetTesteeDataItemList(int id, int scaleId) throws ClassNotFoundException, SQLException, IndexOutOfBoundsException;
	
	String GetGroups(int id, int scaleId) throws ClassNotFoundException, SQLException;
}
