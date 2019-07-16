package com.mayi.jiagousi.ch18_spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aop {

    @Before("execution(* com.mayi.jiagousi.ch18_spring.UserService.add(..))")
    public void before(){
        System.out.println("..........前置通知..........");
    }

    @After("execution(* com.mayi.jiagousi.ch18_spring.UserService.add(..))")
    public void after(){
        System.out.println("..........后置通知..........");
    }

    @AfterReturning("execution(* com.mayi.jiagousi.ch18_spring.UserService.add(..))")
    public void afterReturning(){
        System.out.println("..........运行通知..........");
    }

    @AfterThrowing("execution(* com.mayi.jiagousi.ch18_spring.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("..........异常通知..........");
    }

    @Around("execution(* com.mayi.jiagousi.ch18_spring.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("..........环绕通知前..........");
        // 如果调用该方法则执行方法，如果不通过，则不执行方法
        proceedingJoinPoint.proceed();
        System.out.println("..........环绕通知后..........");
    }
}
