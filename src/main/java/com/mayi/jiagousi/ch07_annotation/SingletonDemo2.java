package com.mayi.jiagousi.ch07_annotation;

public class SingletonDemo2 {

    private static SingletonDemo2 singleton = new SingletonDemo2();

    private SingletonDemo2(){}

    private static SingletonDemo2 getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo2.getInstance());
            }).start();
        }
    }
}
