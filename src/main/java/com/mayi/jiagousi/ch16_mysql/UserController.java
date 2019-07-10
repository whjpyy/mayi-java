package com.mayi.jiagousi.ch16_mysql;

import com.mayi.jiagousi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String register(String name, String pwd){
        return userService.register(name, pwd);
    }

    @RequestMapping("/get")
    public String register(long id){
        return userService.get(id);
    }
}
