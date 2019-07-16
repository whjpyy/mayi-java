package com.mayi.jiagousi.ch18_spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

public class Aop2 {
    @Autowired
    private TransactionUtils transactionUtils;
    private TransactionStatus status;

    public void before(){
        System.out.println("..........2前置通知..........");
    }

    public void after(){
        System.out.println("..........2后置通知..........");
    }

    public void afterReturning(){
        System.out.println("..........2运行通知..........");
    }

    public void afterThrowing(){
        System.out.println("..........2异常通知..........");
        transactionUtils.rollback(status);
    }

    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("..........2环绕通知前..........");
        this.status = transactionUtils.begin();
        // 如果调用该方法则执行方法，如果不通过，则不执行方法
        proceedingJoinPoint.proceed();
        System.out.println("..........2环绕通知后..........");
        transactionUtils.commit(status);
    }
}
