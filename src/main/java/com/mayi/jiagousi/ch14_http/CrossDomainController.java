package com.mayi.jiagousi.ch14_http;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CrossDomainController {

    @RequestMapping("/crossDomain")
    public String crossDomain(){
        return "cross_domain";
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @ResponseBody
    public String getName(String name, HttpServletResponse resp){
        System.out.println("name=" + name);
        // 允许浏览器跨域访问
        resp.setHeader("Access-Control-Allow-Origin", "*");
        return "hello, " + name;
    }

    @RequestMapping(value = "/jsonptest", method = RequestMethod.GET)
    @ResponseBody
    public String jsonp(String name, HttpServletRequest req){
        String callback = req.getParameter("callback");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jsonp, " + name);
        return callback + "(" + jsonObject.toJSONString() + ")";
    }
}
