package com.mayi.jiagousi.ch18_spring;

import com.mayi.jiagousi.entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User)context.getBean("user1");
        System.out.println(user);
//
//        UserService userService = (UserService) context.getBean("userService");
//        userService.add();
//        userService.add2();

    }
}
