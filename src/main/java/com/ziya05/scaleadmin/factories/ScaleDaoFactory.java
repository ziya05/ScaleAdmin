package com.ziya05.scaleadmin.factories;

import com.ziya05.scaleadmin.dao.IScaleDao;
import com.ziya05.scaleadmin.dao.ScaleDao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ScaleDaoFactory {
	public static IScaleDao createScaleDao() throws NamingException {
		Context sourceCtx = new InitialContext(); 
		DataSource ds = 
				(DataSource)sourceCtx.lookup("java:comp/env/jdbc/scale");
		return new ScaleDao(ds);
	}
}
