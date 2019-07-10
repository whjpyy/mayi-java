package com.mayi.jiagousi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.mayi.jiagousi.mapper")
public class JiagousiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiagousiApplication.class, args);
    }


}
