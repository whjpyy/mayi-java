package com.mayi.jiagousi.ch04;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println("子线程1开始执行...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("子线程1结束执行..");
        }).start();
        new Thread(() -> {
            System.out.println("子线程2开始执行...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("子线程2结束执行..");
        }).start();
        // 等待线程countdown=0，如果不为0，则一直等待
        countDownLatch.await();
        System.out.println("主线程结束");
    }
}
