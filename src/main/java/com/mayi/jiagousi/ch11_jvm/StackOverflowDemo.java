package com.mayi.jiagousi.ch11_jvm;

public class StackOverflowDemo {
    // -Xss5m 深度

    private static int count = 0;
    public static void getCount(){
        try {
            count++;
            getCount();
        }catch (Throwable e){
            System.out.println("最大的深度..." + count);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getCount();
    }
}
