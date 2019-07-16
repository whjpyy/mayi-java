package com.mayi.mayispringboot.controller;

import com.mayi.mayispringboot.entity.User;
import com.mayi.mayispringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/name")
    @ResponseBody
    public String getName(int id){
        return userService.getName(id);
    }

    @RequestMapping("/user/query")
    @ResponseBody
    public User getByName(String name){
        return userService.getUserByName(name);
    }
}
