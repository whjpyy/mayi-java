package com.mayi.jiagousi.ch05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 */
public class ReentryLockDemo2 extends Thread{
    private Lock lock = new ReentrantLock();

    public void get(){
        lock.lock();
        System.out.println(getName() + ",get");
        set();
        lock.unlock();
    }

    public void set(){
        System.out.println(getName() + ",set");
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new ReentryLockDemo2().start();
        }
    }
}
