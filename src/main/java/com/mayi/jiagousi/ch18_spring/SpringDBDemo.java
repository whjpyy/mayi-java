package com.mayi.jiagousi.ch18_spring;

import com.mayi.jiagousi.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDBDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

//        UserService userService = (UserService) context.getBean("userService");
////        userService.insert("chen", 29);
////        userService.insert2("chen", 29);
//        userService.insert3();

        UserService2 userService2 = (UserService2) context.getBean("userService2");
        userService2.add();
    }
}
