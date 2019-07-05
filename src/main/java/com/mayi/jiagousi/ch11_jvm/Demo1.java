package com.mayi.jiagousi.ch11_jvm;

public class Demo1 {
    private static int count = 0;

    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        d1.count++;
        Demo1 d2 = new Demo1();
        d2.count++;
        System.out.println(d1.count);
    }
}
