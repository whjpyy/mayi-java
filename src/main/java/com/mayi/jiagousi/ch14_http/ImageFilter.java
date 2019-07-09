package com.mayi.jiagousi.ch14_http;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片防盗链
 */
@WebFilter(urlPatterns = "/images/*", filterName = "imageFilter")
public class ImageFilter implements Filter {
    private static String MY_DOMAIN = "a.a.com";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("图片过滤器开始初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;
        String referer = req.getHeader("referer");
        // 请求的服务名称
        String serverName = req.getServerName();
        System.out.println("serverName:" + serverName + ",referer: " + referer);
        // 如果referer为空，或者referer的上级不包含我们的域名
        if(referer == null || !referer.contains(MY_DOMAIN)){
            req.getRequestDispatcher("/images/forbidden.jpg").forward(req, res);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
