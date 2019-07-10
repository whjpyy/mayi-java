package com.mayi.jiagousi.ch14_http;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do xssFilter...");
        HttpServletRequest req = (HttpServletRequest) request;
        XssHttpServletRequest xssHttpServletRequest = new XssHttpServletRequest(req);
        chain.doFilter(xssHttpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
