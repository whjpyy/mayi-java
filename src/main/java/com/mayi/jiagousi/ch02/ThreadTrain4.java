package com.mayi.jiagousi.ch02;

/**
 * 正面同步方法使用this锁
 */
public class ThreadTrain4 implements Runnable {
    // 火车票总数
    public static int count = 100;
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
                // 把obj换成ThreadTrain4.class, 则可以同步
                synchronized (ThreadTrain4.class) {
                    if (count > 0) {
                        System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。");
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

    public static synchronized void sell() {
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

    public static void main(String[] args) throws InterruptedException {
        ThreadTrain4 threadTrain = new ThreadTrain4();
//        System.out.println("threadTran: " + threadTrain);

        new Thread(threadTrain).start();

        Thread.sleep(100);
        threadTrain.flag = false;
        new Thread(threadTrain).start();
    }
}
