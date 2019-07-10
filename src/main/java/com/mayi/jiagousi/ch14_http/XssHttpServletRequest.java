package com.mayi.jiagousi.ch14_http;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        System.out.println("没有转义的值：" + value);
        if(!StringUtils.isEmpty(value)){
            value = StringEscapeUtils.escapeHtml(value);
            System.out.println("转义后的值：" + value);
        }
        return value;
    }
}
