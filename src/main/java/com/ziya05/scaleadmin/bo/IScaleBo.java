package com.ziya05.scaleadmin.bo;

import java.sql.SQLException;

import com.ziya05.scaleadmin.beans.TesteeBaseBean;

public interface IScaleBo {
	TesteeBaseBean getTesteeDataById(int id, int scaleId) throws ClassNotFoundException, SQLException, IndexOutOfBoundsException;
}
