package com.ziya05.scaleadmin.factories;

import com.ziya05.scaleadmin.dao.IScaleDao;
import com.ziya05.scaleadmin.dao.ScaleDao;
import com.ziya05.scaleadmin.dao.FixScaleDao;

public class ScaleDaoFactory {
	public static IScaleDao createScaleDao() {
		return new ScaleDao();
	}
}
