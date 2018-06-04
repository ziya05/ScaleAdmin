package com.ziya05.scaleadmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

public class Login extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{  
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	
	 public void doPost(HttpServletRequest request,
             HttpServletResponse response)
     throws ServletException, IOException
	{    
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
		
		if (!toLogin) {
			if (!(userName.equals("1") && password.equals("1"))) {
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
			session.setAttribute("user", userName);
			response.sendRedirect("Index");
		}
	}
}
