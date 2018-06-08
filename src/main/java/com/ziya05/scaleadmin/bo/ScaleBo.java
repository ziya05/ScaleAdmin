package com.ziya05.scaleadmin.bo;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.scaleadmin.beans.FactorScoreLevelBean;
import com.ziya05.scaleadmin.beans.ResultAdviceBean;
import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.beans.TesteeDataItemBean;
import com.ziya05.scaleadmin.beans.TesteePersonalInfoBean;
import com.ziya05.scaleadmin.dao.IScaleDao;
import com.ziya05.scaleadmin.factories.ScaleDaoFactory;

public class ScaleBo implements IScaleBo {
	IScaleDao dao = ScaleDaoFactory.createScaleDao();
	
	public TesteeBaseBean getTesteeDataById(int id, int scaleId) 
			throws ClassNotFoundException, SQLException,IndexOutOfBoundsException {
		TesteeBaseBean bean = dao.GetTesteeBase(id, scaleId);
		TesteePersonalInfoBean info = dao.GetTesteePersonalInfo(id, scaleId);
		bean.setPersonalInfo(info);
		
		List<TesteeDataItemBean> items = dao.GetTesteeDataItemList(id, scaleId);
		bean.setItems(items);
		
		String groups = dao.GetGroups(id, scaleId);
		bean.setGroups(groups);
		
		return bean;
	}

	public List<ResultAdviceBean> GetResultAdviceList(int id, int scaleId) throws ClassNotFoundException, SQLException {
		return dao.GetResultAdviceList(id, scaleId);
	}

	public List<FactorScoreLevelBean> GetFactorScoreLevelList(int id, int scaleId)
			throws ClassNotFoundException, SQLException {
		return dao.GetFactorScoreLevelList(id, scaleId);
	}

}
