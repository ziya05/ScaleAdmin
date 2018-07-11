package com.ziya05.scaleadmin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class EncodingFilter extends HttpServlet implements Filter {  
    private String encoding = null;  
    public void init(FilterConfig filterConfig) throws ServletException {  
        encoding = filterConfig.getInitParameter("encoding");  
    }  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        request.setCharacterEncoding(encoding);  
        response.setCharacterEncoding(encoding);  
        chain.doFilter(request, response);  
    }  
}  