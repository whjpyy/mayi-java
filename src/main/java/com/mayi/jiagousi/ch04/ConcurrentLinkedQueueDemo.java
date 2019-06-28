package com.mayi.jiagousi.ch04;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.add("张三");
        queue.add("李四");
        System.out.println(queue.size());
        System.out.println(queue.poll());   // 从队列中取出来
        System.out.println(queue.peek());   // 不从队列中取出来
        System.out.println(queue.size());
    }
}
