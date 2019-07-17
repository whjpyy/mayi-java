package com.mayi.mayispringboot.service;

import com.mayi.mayispringboot.entity.User;
import com.mayi.mayispringboot.mapper.UserMapper;
import com.mayi.mayispringboot.mapper.test01.User1Mapper;
import com.mayi.mayispringboot.mapper.test02.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;


    public String getName(int id){
        String name = jdbcTemplate.queryForObject("select name from user where id = " + id, String.class);
        return name;
    }

    public User getUserByName(String name){
        return userMapper.findByName(name);
    }

    @Transactional
    public void insert1(){
        User user = new User();
        user.setName("分布式事务");
        user.setAge(11);
        user1Mapper.insert(user);
        user2Mapper.insert(user);
        int i = 1 / 0;
    }

    @Async
    public void sendMsg(){
        System.out.println("*********sendMsg3**********");
        System.out.println("*********sendMsg4**********");
    }

}
