package com.mayi.jiagousi.ch03;

/**
 * 共享资源
 */
class Res{
    public String name;
    public String sex;
    public boolean flag = false;
}

class InputThread extends Thread{
    private Res res;
    public InputThread(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res) {
                if(res.flag == true) {
                    // 进行休眠，并且释放锁，wait必须跟同步的资源一起使用
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    if (count == 0) {
                        res.name = "陈友增";
                        res.sex = "男";
                    } else {
                        res.name = "张玉婷";
                        res.sex = "女";
                    }
                    count = (count + 1) % 2;
                    res.flag = true;
                    // 唤醒另一个线程
                    res.notify();
                }
            }
        }
    }
}

class OutputThread extends Thread{
    private Res res;
    public OutputThread(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            synchronized (res) {
                if(res.flag) {
                    System.out.println(res.name + "------" + res.sex);
                    res.flag = false;
                    res.notify();
                }else{
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

/**
 * 通过flag实现生产一次，消费一次
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        Res res = new Res();

        new InputThread(res).start();
        new OutputThread(res).start();
    }
}
