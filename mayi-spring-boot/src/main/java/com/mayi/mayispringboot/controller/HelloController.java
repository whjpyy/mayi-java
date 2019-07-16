package com.mayi.mayispringboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index(){
        return "success";
    }

    @RequestMapping("/global_error")
    public String error(){
        int i = 1 / 0;
        return "success";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }
}
