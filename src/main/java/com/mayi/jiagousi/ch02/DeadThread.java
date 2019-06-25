package com.mayi.jiagousi.ch02;

/**
 * 死锁
 */
public class DeadThread implements Runnable {
    // 火车票总数
    public static int count = 100;
    public boolean flag = true;
    private Object obj = new Object();

    @Override
    public void run() {
        // flag=true 先拿obj锁，在拿this锁，然后执行代码
        // flag=false 先拿this锁，在拿obj锁，然后执行代码
        if (flag) {
            while (count > 0) {
                synchronized (obj) {
                    sell();
                }
            }
        } else {
            while (count > 0) {
                sell();
            }
        }
    }

    public synchronized void sell() {
        synchronized (obj) {
            if (count > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。");
                count--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadThread threadTrain = new DeadThread();

        new Thread(threadTrain).start();

        Thread.sleep(100);
        threadTrain.flag = false;
        new Thread(threadTrain).start();
    }
}
