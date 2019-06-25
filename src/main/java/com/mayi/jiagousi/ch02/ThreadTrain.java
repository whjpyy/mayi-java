package com.mayi.jiagousi.ch02;

/**
 * 使用同步代码块
 */
public class ThreadTrain implements Runnable{
    // 火车票总数
    private int count = 100;
    @Override
    public void run() {
        System.out.println(this);
        while (count > 0){
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if(count > 0) {
                    System.out.println(Thread.currentThread().getName() + "出售第" + (101 - count) + "张票。");
                    count--;
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadTrain threadTrain = new ThreadTrain();
        System.out.println("threadTran: " + threadTrain);

        new Thread(threadTrain).start();
        new Thread(threadTrain).start();
    }
}
