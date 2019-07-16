package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(){
        jdbcTemplate.update("insert into log values(null, now())");
        System.out.println("日志添加完毕");
    }
}
