package com.mayi.jiagousi.ch16_mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulusDividingTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 水平分割取模
     */
    @Test
    public void test1(){
        // 插入数据
        for(int i = 0;i < 10;i ++) {
            // 生成uuid
            jdbcTemplate.update("insert into uuid values(null)");
            Long userId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
            // 存放在具体的表中
            String tableName = "user" + userId % 3;
            jdbcTemplate.update("insert into " + tableName + " values (" + userId + ", 'name" + userId + "', 'pwd" + userId + "')");
        }
    }
}
