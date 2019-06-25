package com.mayi.jiagousi.ch01.thread;

import java.util.ArrayList;
import java.util.List;

class UserThread extends Thread{
    private List<User> users;

    public UserThread(List<User> users) {
        this.users = users;
    }

    @Override
    public void run() {
        for(int i = 0;i < users.size();i ++){
            System.out.println("threadName: " + getName() + "," + users.get(i));
        }
    }
}

public class BatchThread {

    public static void main(String[] args) {
        // 1.初始化用户
        List<User> users = initUser();
        // 2.定义每一个线程最多跑多少数据
        int userCount = 2;
        // 3.计算每个线程数，并且计算每个线程跑那些数据
        final List<List<User>> splitList = ListUtils.splitList(users, userCount);
        // 4.让子线程进行分批异步处理数据
        for(int i = 0;i < splitList.size();i ++){
//            System.out.println("i:" + i + "---" + splitList.get(i));
            new UserThread(splitList.get(i)).start();
        }
    }


    public static List<User> initUser(){
        List<User> list = new ArrayList<>();
        for(int i = 0;i < 10;i ++){
            list.add(new User("userId:" + i, "userName:" + i));
        }
        return list;
    }
}
