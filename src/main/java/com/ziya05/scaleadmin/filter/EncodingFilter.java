package com.ziya05.scaleadmin.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class EncodingFilter extends HttpServlet implements Filter {  
	
    private String encoding = null;  
    private Pattern excepUrlPattern;
    
    public void init(FilterConfig cfg) throws ServletException {  
        encoding = cfg.getInitParameter("encoding"); 
        
        String excepUrlRegex = cfg.getInitParameter("excepUrlRegex");
		if (!StringUtils.isBlank(excepUrlRegex)) {
			excepUrlPattern = Pattern.compile(excepUrlRegex, Pattern.CASE_INSENSITIVE);
		}
    }  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  
        
    	HttpServletRequest request = (HttpServletRequest) req;
    	String servletPath = request.getServletPath();    	
    	
    	if(excepUrlPattern.matcher(servletPath).matches()) {
			chain.doFilter(req, res);
			return;		
		}    	
    	
    	req.setCharacterEncoding(encoding);  
        res.setCharacterEncoding(encoding);  
        chain.doFilter(request, res);  
    }  
}  