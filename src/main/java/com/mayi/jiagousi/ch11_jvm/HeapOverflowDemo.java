package com.mayi.jiagousi.ch11_jvm;

import java.util.ArrayList;

public class HeapOverflowDemo {

    public static void main(String[] args) {
        // -Xms1m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
        // -Xms1m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("i: " + i);
            list.add(new byte[1 * 1024 * 1024]);
        }
        System.out.println("创建完毕");
    }
}
