package com.mayi.jiagousi.ch01.thread;

public class JoinThread2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("T1: " +  i);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 20; i++) {
                System.out.println("T2: " +  i);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 20; i++) {
                System.out.println("T3: " +  i);
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
