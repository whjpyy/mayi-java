package com.mayi.jiagousi.ch03;

public class InterruptDemo implements Runnable {

    private volatile static boolean on = true;

    @Override
    public void run() {
        while (on) {
            /*if(Thread.currentThread().isInterrupted()){
                System.out.println("Yes, I am interrupted, but i am still running");
            }else{
                System.out.println("not yet interrupted");
            }*/
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread test = new Thread(new InterruptDemo(), "InterruptDemo");
        test.start();
        Thread.sleep(50);
        InterruptDemo.on = false;
        test.interrupt();
        System.out.println("main end");
    }
}
