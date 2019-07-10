package com.mayi.jiagousi.ch16_mysql;

import com.mayi.jiagousi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String register(String name, String pwd){
        // 生成uuid
        jdbcTemplate.update("insert into uuid values(null)");
        Long userId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
        // 存放在具体的表中
        String tableName = "user" + userId % 3;
        jdbcTemplate.update("insert into " + tableName + " values (" + userId + ", '" + name + "', 'pwd" + pwd + "')");
        return "success";
    }

    public String get(Long userId){
        // 存放具体的表中
        String tableName = "user" + userId % 3;
        String sql = "select name from " + tableName + " where id = " + userId;
        System.out.println(sql);
        String name = jdbcTemplate.queryForObject(sql, String.class);
        return name;
    }
}
