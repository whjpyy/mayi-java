package com.mayi.jiagousi.ch02;

/**
 * 正面同步方法使用this锁
 */
public class ThreadTrain3 implements Runnable {
    // 火车票总数
    private int count = 100;
    public boolean flag = true;
    private Object obj = new Object();

    @Override
    public void run() {
        if (flag) {
            while (count > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 把obj换成this, 则可以同步
                synchronized (this) {
                    if (count > 0) {
                        System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。" + flag);
                        count--;
                    }
                }
            }
        } else {
            while (count > 0) {
                sell();
            }
        }
    }

    public synchronized void sell() {
        if (count > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。" + flag);
            count--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain3 threadTrain = new ThreadTrain3();
//        System.out.println("threadTran: " + threadTrain);

        new Thread(threadTrain).start();

        Thread.sleep(100);
        threadTrain.flag = false;
        new Thread(threadTrain).start();
    }
}
