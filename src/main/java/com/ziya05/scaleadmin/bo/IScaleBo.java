package com.ziya05.scaleadmin.bo;

import java.sql.SQLException;
import java.util.List;

import com.ziya05.scaleadmin.beans.BaseScaleBean;
import com.ziya05.scaleadmin.beans.FactorScoreLevelBean;
import com.ziya05.scaleadmin.beans.ResultAdviceBean;
import com.ziya05.scaleadmin.beans.TesteeBaseBean;

public interface IScaleBo {
	BaseScaleBean getBaseScale(int scaleId) throws ClassNotFoundException, SQLException;
	
	TesteeBaseBean getTesteeDataById(int id, int scaleId) throws ClassNotFoundException, SQLException, IndexOutOfBoundsException;
	
	List<ResultAdviceBean> GetResultAdviceList(int id, int scaleId) throws ClassNotFoundException, SQLException;
	
	List<FactorScoreLevelBean> GetFactorScoreLevelList(int id, int scaleId) throws ClassNotFoundException, SQLException;
	
	List<FactorScoreLevelBean> GetFactorScoreLevelListForChart(int id, int scaleId) throws ClassNotFoundException, SQLException;
}
