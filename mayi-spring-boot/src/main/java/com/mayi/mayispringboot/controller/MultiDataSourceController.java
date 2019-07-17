package com.mayi.mayispringboot.controller;

import com.mayi.mayispringboot.entity.User;
import com.mayi.mayispringboot.mapper.test01.User1Mapper;
import com.mayi.mayispringboot.mapper.test02.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi")
public class MultiDataSourceController {

    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/test01")
    public String test01(){
        User user = new User();
        user.setName("chen");
        user.setAge(30);
        int count = user1Mapper.insert(user);
        return count == 1 ? "success" : "fail";
    }

    @RequestMapping("/test02")
    public String test02(){
        User user = new User();
        user.setName("you");
        user.setAge(31);
        int count = user2Mapper.insert(user);
        return count == 1 ? "success" : "fail";
    }
}
