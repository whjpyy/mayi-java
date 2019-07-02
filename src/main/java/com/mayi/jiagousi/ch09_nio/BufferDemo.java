package com.mayi.jiagousi.ch09_nio;


import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferDemo {

    /**
     *     private int mark = -1;
     *     private int position = 0;    缓冲区正在操作的位置，从0开始
     *     private int limit;       界面（缓存区可用大小）
     *     private int capacity;    缓冲区最大容量，一旦声明不能改变
     */
    @Test
    public void test01(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("-----------------");
        System.out.println("往bytebuffer中存放数据");
        byteBuffer.put("abcd1".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        System.out.println("读取值");
        // 开启读取模式
        byteBuffer.flip();  // position从0开始
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("重复读取");
        byteBuffer.rewind();    // 从上次读取的节点开始
        bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("清空缓冲区");
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

    }
}
