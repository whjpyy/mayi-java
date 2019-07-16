package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService2 {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LogDao logDao;

    public void add(){
        userDao.insert("chen", 31);
        int i = 1 / 0;
    }

}
