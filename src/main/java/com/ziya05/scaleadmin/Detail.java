package com.ziya05.scaleadmin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ziya05.scaleadmin.beans.FactorScoreLevelBean;
import com.ziya05.scaleadmin.beans.ResultAdviceBean;
import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.bo.IScaleBo;
import com.ziya05.scaleadmin.factories.ScaleBoFactory;

public class Detail extends HttpServlet {
	IScaleBo bo = null;
	
	public Detail() throws NamingException {
		super();
		
		bo = ScaleBoFactory.createScaleBo();
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{  
		String strId = request.getParameter("id");
		String strScaleId = request.getParameter("scaleId");
		String type = request.getParameter("type");
		
		int id = Integer.parseInt(strId);
		int scaleId = Integer.parseInt(strScaleId);
		
		String page = null;
		if (type.equals("base")) {
			try {
				TesteeBaseBean bean = bo.getTesteeDataById(id, scaleId);
				request.setAttribute("baseData", bean);				
				
				List<FactorScoreLevelBean> fslLst = bo.GetFactorScoreLevelList(id, scaleId);
				request.setAttribute("fslLst", fslLst);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			page = "/base.jsp";
			
		} else {
			try {
				TesteeBaseBean base = bo.getTesteeDataById(id, scaleId);
				List<ResultAdviceBean> lst = bo.GetResultAdviceList(id, scaleId);
				clear(lst, base);
				request.setAttribute("adviceLst", lst);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			page = "/advice.jsp";
			
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	private void clear(List<ResultAdviceBean> lst, TesteeBaseBean base) {
		for (ResultAdviceBean bean : lst) {
			if (StringUtils.isAllBlank(bean.getDescription())) {
				bean.setDescription("无");
			}
			if (StringUtils.isAllBlank(bean.getAdvice())) {
				bean.setAdvice("无");
			}
			
			bean.setAdvice(bean.getAdvice().replaceAll("\\{USERNAME\\}", base.getUserName()));
			bean.setDescription(bean.getDescription().replaceAll("\\{USERNAME\\}", base.getUserName()));
		}
	}
}
