package com.mayi.jiagousi.ch03;

/**
 * 停止线程demo
 */
public class StopThread extends Thread {

    public boolean flag = true;

    @Override
    public void run() {
        while (flag){
            System.out.println(Thread.currentThread().getName() + "---我是子线程");
        }
    }

    public void stopThread(){
        flag = false;
        System.out.println(Thread.currentThread().getName() + "----线程被停止");
    }

    public static void main(String[] args) {
        StopThread thread1 = new StopThread();
        StopThread thread2 = new StopThread();
        thread1.start();
        thread2.start();
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 0){
                thread1.stopThread();
                thread2.stopThread();
            }
        }
    }
}
