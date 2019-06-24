package com.mayi.jiagousi.thread;



public class ThreadDemo2 implements Runnable{

    public static void main(String[] args) {
        new Thread(new ThreadDemo2()).start();
    }

    @Override
    public void run() {
        for(int i = 0;i < 100;i ++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
