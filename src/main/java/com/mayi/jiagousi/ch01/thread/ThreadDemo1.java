package com.mayi.jiagousi.ch01.thread;

public class ThreadDemo1 extends Thread{

    @Override
    public void run() {
        for(int i = 0;i < 100;i ++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public static void main(String[] args) {
        new ThreadDemo1().start();
    }
}
