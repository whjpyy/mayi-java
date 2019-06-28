package com.mayi.jiagousi.ch04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


class Consumer implements Runnable{
    private BlockingQueue<Integer> queue;
    private boolean flag = true;
    private static AtomicInteger count = new AtomicInteger(0);

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("消费者线程开始...");
        while (flag){
            try {
                Integer data = queue.poll(2, TimeUnit.SECONDS);
                if(data != null){
                    System.out.println("消费成功： " + data);
                }else{
                    System.out.println("消费失败： " + data);
                    this.flag = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("消费者线程结束");
            }
        }
    }
}

public class ProducerDemo implements Runnable{

    private BlockingQueue<Integer> queue;
    private volatile boolean flag = true;
    private static AtomicInteger count = new AtomicInteger(0);

    public ProducerDemo(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动...");
        while (flag){
            try {
                int data = count.incrementAndGet();
                boolean offer = queue.offer(data);
                if (offer){
                    System.out.println("生产者添加队列成功：" + data);
                }else {
                    System.out.println("生产者添加队列失败：" + data);
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者线程终止...");
            }
        }
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        ProducerDemo p1 = new ProducerDemo(queue);
//        ProducerDemo p2 = new ProducerDemo(queue);
        Consumer c1 = new Consumer(queue);
        new Thread(p1).start();
//        new Thread(p2).start();
        new Thread(c1).start();


        Thread.sleep(10000);
        p1.setFlag(false);
//        p2.setFlag(false);
    }
}
