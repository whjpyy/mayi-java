package com.mayi.jiagousi.ch04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.add("张三");
        queue.add("李四");
        queue.add("hehe");
        // 可以阻塞的队列
        boolean offer = queue.offer("王五", 2, TimeUnit.SECONDS);
        System.out.println("添加失败" + offer);
        System.out.println(queue.size());
    }
}
