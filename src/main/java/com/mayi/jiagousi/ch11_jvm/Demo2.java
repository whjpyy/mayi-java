package com.mayi.jiagousi.ch11_jvm;

import java.text.DecimalFormat;

/**
 * JVM内存调优
 */
public class Demo2 {

    private static String toM(long maxMMemory){
        float num = (float) maxMMemory / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00"); // 格式化小数
        String s = df.format(num);
        return s;
    }

    public static void jvmInfo(){
        // 最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory:" + maxMemory + "," + toM(maxMemory) + "M");
        // 空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory: " + freeMemory + "," + toM(maxMemory) + "M");
        // 已使用内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory: " + totalMemory + "," + toM(totalMemory) + "M");

    }

    public static void main(String[] args) throws InterruptedException {
        // 设置JVM参数
        // -Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags 2次GC
        // -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags 1次GC
        byte[] bytes1 = new byte[1*1024*1024];
        System.out.println("分配了1M内存");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes2 = new byte[4*1024*1024];
        System.out.println("分配了4M内存");
        jvmInfo();
    }

}
