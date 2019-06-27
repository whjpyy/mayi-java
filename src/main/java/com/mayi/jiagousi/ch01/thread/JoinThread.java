package com.mayi.jiagousi.ch01.thread;

/**
 * join会让调用该方法的线程先执行
 */
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    System.out.println("子线程， " + i);
                }
            }
        });
        t1.start();
        t1.join();
        for (int i = 0; i < 30; i++) {
            System.out.println("主线程：" + i);
        }
    }
}
