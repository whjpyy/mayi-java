package com.mayi.springbootsession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionController {
    @Value("${server.port}")
    private String PORT;

    @RequestMapping("/session/set")
    public String setSession(HttpServletRequest request, String key, String value){
        HttpSession session = request.getSession(true);
        session.setAttribute(key, value);
        return "success, port" +  PORT;
    }

    @RequestMapping("/session/get")
    public String getSession(HttpServletRequest request, String key){
        HttpSession session = request.getSession(false);
        return "success, port" + PORT + ", value=" + session.getAttribute(key);
    }
}
