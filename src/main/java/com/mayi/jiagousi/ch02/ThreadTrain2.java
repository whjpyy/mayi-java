package com.mayi.jiagousi.ch02;

/**
 * 使用同步方法
 */
public class ThreadTrain2 implements Runnable {
    // 火车票总数
    private int count = 100;

    @Override
    public void run() {
        System.out.println(this);
        while (count > 0) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sell();
        }
    }

    public synchronized void sell() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。");
            count--;
        }
    }

    public static void main(String[] args) {
        ThreadTrain2 threadTrain = new ThreadTrain2();
        System.out.println("threadTran: " + threadTrain);

        new Thread(threadTrain).start();
        new Thread(threadTrain).start();
    }
}
