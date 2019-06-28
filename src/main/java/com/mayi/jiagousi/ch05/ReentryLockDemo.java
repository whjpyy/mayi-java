package com.mayi.jiagousi.ch05;

/**
 * 可重入锁
 */
public class ReentryLockDemo extends Thread{

    public synchronized void get(){
        System.out.println(getName() + ",get");
        set();
    }

    public synchronized void set(){
        System.out.println(getName() + ",set");
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new ReentryLockDemo().start();
        }
    }
}
