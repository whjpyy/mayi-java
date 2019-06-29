package com.mayi.jiagousi.ch07_annotation;

/**
 * 懒汉式
 */
public class SingletonDemo {

    private static SingletonDemo singleton;

    private SingletonDemo(){}

    public static SingletonDemo getInstance(){
        if(singleton == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonDemo.class) {  // 第一层上锁
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(singleton == null) {
                    singleton = new SingletonDemo();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        // 有线程安全问题
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(SingletonDemo.getInstance());
            }).start();
        }
    }
}
