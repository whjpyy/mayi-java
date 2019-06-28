package com.mayi.jiagousi.ch03;

public class ThreadLocalDemo extends Thread{
    private Res res;

    public ThreadLocalDemo(Res res) {
       this.res = res;
    }

    static class Res{
        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        public int getCount() {
            threadLocal.set(threadLocal.get() + 1);
            return threadLocal.get();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName() + "," + res.getCount());
        }
    }

    public static void main(String[] args) {
        Res res = new Res();
        new ThreadLocalDemo(res).start();
        new ThreadLocalDemo(res).start();
        new ThreadLocalDemo(res).start();


    }
}
