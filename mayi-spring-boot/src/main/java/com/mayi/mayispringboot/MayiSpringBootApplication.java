package com.mayi.mayispringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mayi.mayispringboot.mapper")
public class MayiSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayiSpringBootApplication.class, args);
    }

}
