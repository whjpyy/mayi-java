package com.mayi.jiagousi.ch02;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread{

    // 10个线程共享count
    // static修饰关键字，存放静态去，只会存放一次，所有线程中都共享
//    private volatile static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
//            count++;
            count.incrementAndGet();
        }
        System.out.println(getName() + "," + count.get());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new VolatileNoAtomic().start();
        }
    }
}
