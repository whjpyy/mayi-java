package com.mayi.jiagousi.ch05;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Cache {

    private static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock r = lock.readLock();
    static Lock w = lock.writeLock();

    public static Object put(String key, Object obj) {
        try {
            w.lock();
            System.out.println("写入，key:" + key + ",value:" + obj + " 开始...");
            Thread.sleep(100);
            map.put(key, obj);
            System.out.println("写入，key:" + key + ",value:" + obj + " 结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
        return obj;
    }

    public static void get(String key) {
        try {
            r.lock();
            System.out.println("读取，key:" + key + " 开始...");
            Thread.sleep(100);
            Object obj = map.get(key);
            System.out.println("读取，key:" + key + ",value:" + obj + " 结束...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Cache.put("k_" + i, "v_" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Cache.get("k_" + i);
            }
        }).start();

        // 读取，key:k_7,value:null 结束...
        // 读会在写之前
    }

}
