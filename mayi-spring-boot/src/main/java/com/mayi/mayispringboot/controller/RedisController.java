package com.mayi.mayispringboot.controller;

import com.mayi.mayispringboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/setString")
    public String setString(String key, String value){
        redisService.setString(key, value, 1000l);
        return "success";
    }

    @RequestMapping("/getString")
    public String setString(String key){
        return redisService.getKey(key);
    }
}
