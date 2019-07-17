package com.mayi.mayispringboot;

import com.mayi.mayispringboot.config.DBConfig1;
import com.mayi.mayispringboot.config.DBConfig2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.mayi.mayispringboot.mapper")
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
//@EnableScheduling
@EnableAsync
public class MayiSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MayiSpringBootApplication.class, args);
    }

}
