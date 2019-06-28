package com.mayi.jiagousi.ch04;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{
    Semaphore wc;
    String name;

    public SemaphoreDemo(Semaphore wc, String name) {
        this.wc = wc;
        this.name = name;
    }

    @Override
    public void run() {
        if(wc.availablePermits() > 0){
            System.out.println(name + "抢到位置了");
        }else{
            System.out.println("暂时没有位置了，" + name + "正在等待中");
        }
        try {
            // 获取许可
            wc.acquire();
            System.out.println(name + "正在上厕所");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(name + "上完厕所了。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            // 释放
            wc.release();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            new Thread(new SemaphoreDemo(semaphore,  i + "号")).start();
        }
    }
}
