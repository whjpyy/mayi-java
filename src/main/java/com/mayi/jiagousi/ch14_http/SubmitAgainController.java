package com.mayi.jiagousi.ch14_http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class SubmitAgainController {

    @RequestMapping(value = "/resubmit", method = RequestMethod.GET)
    public String resubmit(Model model, HttpServletRequest req){
        String token = UUID.randomUUID().toString();
        model.addAttribute("token", token);
        req.getSession().setAttribute("token", token);
        return "resubmit";
    }


    @RequestMapping(value = "/submitAgain", method = RequestMethod.POST)
    public String submitAgain(String token, HttpServletRequest req, Model model) throws Exception {
        String name = req.getParameter("name");
        if(!isFlag(token, req)){
            System.out.println("已经重复提交");
            model.addAttribute("name", "已经重复提交");
            return "success";
        }
        Thread.sleep(1000);
        model.addAttribute("name", name);
        System.out.println("do something..." + name);
        return "success";
    }

    public boolean isFlag(String token, HttpServletRequest req){
        if(StringUtils.isEmpty(token)){
            System.out.println("token不能为空");
            return false;
        }
        String sessionToken = (String) req.getSession().getAttribute("token");
        if(!token.equalsIgnoreCase(sessionToken)){
            System.out.println("请不要伪造token," + sessionToken);
            return false;
        }
        req.getSession().removeAttribute("token");
        return true;
    }
}
