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

import com.ziya05.scaleadmin.beans.TesteeBaseBean;
import com.ziya05.scaleadmin.dao.IScaleDao;
import com.ziya05.scaleadmin.factories.ScaleDaoFactory;

public class Index extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{  
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{ 
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String date = request.getParameter("date");
		String scaleName = request.getParameter("scaleName");
		
		String strPageIndex = request.getParameter("pageIndex");
		
		int pageIndex = 1;
		if (!StringUtils.isBlank(strPageIndex)) {
			pageIndex = Integer.parseInt(strPageIndex);
		}
		
		int pageLength = 10;
		
		IScaleDao dao = null;
		
		int itemsCount = 0;
		try {
			dao = ScaleDaoFactory.createScaleDao();
			itemsCount = dao.getPageCount(userName, date, scaleName);
		}catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int pageCount = itemsCount / pageLength;
		if (itemsCount % pageLength > 0) {
			pageCount++;
		} 
		if (pageCount == 0) {
			pageCount = 1;
		}
		
		if (pageIndex > pageCount) {
			pageIndex = pageCount;
		} else if (pageIndex < 1) {
			pageIndex = 1;
		}
		
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("itemsCount", itemsCount);
		
		List<TesteeBaseBean> lst = null;
		try {
			lst = dao.getTesteeBaseBeanList(userName, date, scaleName, pageIndex, pageLength);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("userName", userName);
		request.setAttribute("date", date);
		request.setAttribute("scaleName", scaleName);
		request.setAttribute("dataLst", lst);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
