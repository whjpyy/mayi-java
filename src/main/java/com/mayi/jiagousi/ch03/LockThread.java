package com.mayi.jiagousi.ch03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源
 */
class ResLock {
    public String name;
    public String sex;
    public boolean flag = false;
    public Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
}

class InputThread2 extends Thread {
    private ResLock res;

    public InputThread2(ResLock res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                res.lock.lock();
                if (res.flag) {
                    res.condition.await();
                }
                if (count == 0) {
                    res.name = "陈友增1";
                    res.sex = "男";
                } else {
                    res.name = "张玉婷1";
                    res.sex = "女";
                }
                count = (count + 1) % 2;
                res.flag = true;
                res.condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                res.lock.unlock();
            }

        }
    }
}

class OutputThread2 extends Thread {
    private ResLock res;

    public OutputThread2(ResLock res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true) {
            try {
                res.lock.lock();
                if(!res.flag){
                    res.condition.await();
                }
                System.out.println(res.name + "------" + res.sex);
                res.flag = false;
                res.condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                res.lock.unlock();
            }
        }
    }
}

/**
 * 通过flag实现生产一次，消费一次
 */
public class LockThread {

    public static void main(String[] args) {
        ResLock res = new ResLock();

        new InputThread2(res).start();
        new OutputThread2(res).start();
    }
}
