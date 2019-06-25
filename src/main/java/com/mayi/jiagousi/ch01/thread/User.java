package com.mayi.jiagousi.ch01.thread;

public class User {
     private String userId;
     private String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
