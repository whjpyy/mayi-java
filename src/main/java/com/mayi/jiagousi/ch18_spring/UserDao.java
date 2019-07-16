package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(){
        System.out.println("userDao add...");
    }

    public void insert(String name, Integer age){
        String sql = "insert into user(name, age) values(?, ?)";
        int update = jdbcTemplate.update(sql, name, age);
        System.out.println("数据库添加user成功..insert: " + update);
    }
}
