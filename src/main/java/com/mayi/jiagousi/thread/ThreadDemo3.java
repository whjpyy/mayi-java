package com.mayi.jiagousi.thread;

public class ThreadDemo3 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 100;i ++){
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            }
        }).start();
    }
}
