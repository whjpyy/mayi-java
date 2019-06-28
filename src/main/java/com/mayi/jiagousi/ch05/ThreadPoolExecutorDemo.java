package com.mayi.jiagousi.ch05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        // 1.可缓存的线程池 重复利用
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int temp = i;
            cachedThreadPool.execute(() -> {
                System.out.println("cache:" + Thread.currentThread().getName() + ", i: " + temp);
            });
        }

        // 可固定长度的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            fixedThreadPool.execute(()-> System.out.println("Fix:" + Thread.currentThread().getName() + ", i: " + temp));
        }

        // 可定时线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int temp = i;
            scheduledThreadPool.schedule(() -> System.out.println("schedule: " + Thread.currentThread().getName() + ", i:" + temp)
                , 2, TimeUnit.SECONDS);
        }

        // 单线程线程池
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            int temp = i;
//            temp = 3; // temp不能修改，temp只要不修改，默认就是final
            singleThreadPool.execute(() -> System.out.println("single: " + Thread.currentThread().getName() + ", i:" + temp));
        }
        // 停止线程
        singleThreadPool.shutdown();
    }
}
