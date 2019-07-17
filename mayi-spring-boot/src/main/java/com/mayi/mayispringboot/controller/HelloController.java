package com.mayi.mayispringboot.controller;

import com.mayi.mayispringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @Value("${name}")
    private String name;
    @RequestMapping("/index")
    public String index(){
        return "success" + name;
    }

    @RequestMapping("/global_error")
    public String error(){
        int i = 1 / 0;
        return "success";
    }

    @RequestMapping("/sendMsg")
    public void sendMsg(){
        System.out.println("*********sendMsg1**********");
        userService.sendMsg();
        System.out.println("*********sendMsg2**********");
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }
}
