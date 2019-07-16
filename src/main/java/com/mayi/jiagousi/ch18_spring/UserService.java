package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private TransactionUtils transactionUtils;

    public void add(){
        userDao.add();
        System.out.println("userService add...");
    }

    /**
     * 声明式AOP
     */
    public void add2(){
        userDao.add();
    }

    public void insert(String name, Integer age){
        TransactionStatus transactionStatus = transactionUtils.begin();
        try {
            userDao.insert(name, age);
            int i = 1 / 0;
            transactionUtils.commit(transactionStatus);
        }catch (Exception e){
            transactionUtils.rollback(transactionStatus);
        }
        transactionUtils.commit(transactionStatus);
    }

    public void insert2(String name, Integer age){
        userDao.insert(name, age);
        int i = 1 / 0;
    }

    @Transactional
    public void insert3(){
        logDao.add();
        System.out.println("userService");
        userDao.insert("chen", 30);
        int i = 1 / 0;
    }

}
