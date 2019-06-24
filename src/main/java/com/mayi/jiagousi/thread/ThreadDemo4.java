package com.mayi.jiagousi.thread;

public class ThreadDemo4 {

    public static void main(String[] args) {
        // run方法不能抛出异常，只能catch
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    // 让当前线程从运行状态变为休眠状态，时间到期后会变成运行状态
                    // 不抛出锁，wait可以抛出锁
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + ":" + i);
            }
        }).start();
    }
}
