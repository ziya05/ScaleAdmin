package com.ziya05.scaleadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ziya05.scaleadmin.beans.UserBean;
import com.ziya05.scaleadmin.dao.IScaleDao;
import com.ziya05.scaleadmin.factories.ScaleDaoFactory;

public class Login extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{  
		String redirect = request.getParameter("redirect");
		if (StringUtils.isAllBlank(redirect)) {
			request.setAttribute("redirect", "?redirect=" + redirect);
		}
		
		System.out.println("redirect1: " + redirect);
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	
	 public void doPost(HttpServletRequest request,
             HttpServletResponse response)
     throws ServletException, IOException
	{    
		String redirect = request.getParameter("redirect");
		System.out.println("redirect2: " + redirect);
		if (StringUtils.isAllBlank(redirect)) {
			request.setAttribute("redirect", "?redirect=" + redirect);
		}
		
		 
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		boolean toLogin = false;
		if (StringUtils.isBlank(userName)) {
			request.setAttribute("error", "用户名不能为空！");
			toLogin = true;
		} else if (StringUtils.isBlank(password)) {
			request.setAttribute("error", "密码不能为空！");
			toLogin = true;
		} 
		
		UserBean userBean = null;
		if (!toLogin) {
			IScaleDao dao;

			try {
				dao = ScaleDaoFactory.createScaleDao();
				userBean = dao.getUserBean(userName, password);
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
			if (userBean == null) {
				request.setAttribute("error", "用户名或者密码错误！");
				toLogin = true;
			}
		}
		
		if (toLogin) {
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", userBean);
			
			System.out.println("跳转路径为： " + redirect);
			
			if (StringUtils.isAllBlank(redirect)) {
				response.sendRedirect("Index");
			} else {
				response.sendRedirect(redirect);
			}
						
		}
	}
}
