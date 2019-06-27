package com.mayi.jiagousi.ch02;

public class VolatileDemo extends Thread{

    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始执行...");
        while (flag){}
        System.out.println("子线程结束线程...");
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileDemo demo = new VolatileDemo();
        demo.start();
        Thread.sleep(3000);
        // 主线程修改了值，并没有通知demo线程，demo线程的本地变量flag还是true
        demo.setFlag(false);
        System.out.println("flag已经修改为false");
        Thread.sleep(1000);
        System.out.println(demo.flag);
    }
}
