package com.mayi.jiagousi.ch01.thread;

/**
 * 守护线程和非守护线程
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程：" + i);
                }
            }
        });
        t1.setDaemon(true); // 守护线程
        t1.start();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(400);
            System.out.println("主线程" + i);
        }
    }
}
