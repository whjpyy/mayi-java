package com.mayi.mayispringboot.service;

import com.mayi.mayispringboot.entity.User;
import com.mayi.mayispringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String getName(int id){
        String name = jdbcTemplate.queryForObject("select name from user where id = " + id, String.class);
        return name;
    }

    public User getUserByName(String name){
        return userMapper.findByName(name);
    }

}
