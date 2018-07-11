package com.ziya05.scaleadmin.filter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class SessionFilter implements Filter {
	
	private String sessionKey;
	
	private Pattern excepUrlPattern;
	
	private String redirectUrl;
	
	public void init(FilterConfig cfg) {
		sessionKey = cfg.getInitParameter("sessionKey");
		 
		String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if (!StringUtils.isBlank(excepUrlRegex)) {
			excepUrlPattern = Pattern.compile(excepUrlRegex, Pattern.CASE_INSENSITIVE);
		}
		
		redirectUrl = cfg.getInitParameter("redirectUrl");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (StringUtils.isBlank(sessionKey)) {
			chain.doFilter(req, res);
			return;
		}
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String servletPath = request.getServletPath();
		String type = request.getHeader("X-Requested-With") == null ? ""
				: request.getHeader("X-Requested-With");
		
		if(servletPath.equals(redirectUrl)
				|| excepUrlPattern.matcher(servletPath).matches()) {
			chain.doFilter(req, res);
			return;		
		}
		
		Object sessionObj = request.getSession().getAttribute(sessionKey);
		
		if (sessionObj == null) {

			String contextPath = request.getContextPath();
			String redirect = servletPath + "?"
					+ StringUtils.defaultString(request.getQueryString());  

			String jumpUrl = contextPath + StringUtils.defaultString(redirectUrl, "/") 
					+ "?redirect=" + URLEncoder.encode(redirect, "UTF-8");
			if("XMLHttpRequest".equals(type)) {
				response.setHeader("SESSIONSTATUS", "TIMEOUT");
				response.setHeader("CONTEXTPATH", 
						contextPath + StringUtils.defaultString(redirectUrl, "/"));
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			} else {
				response.sendRedirect(jumpUrl);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
