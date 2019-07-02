package com.mayi.jiagousi.ch09_nio;

import java.nio.ByteBuffer;

public class MarkAndResetDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        String str = "abcd";
        byteBuffer.put(str.getBytes());
        // 开启读
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.mark(); // position=0
        byteBuffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, 2));
        System.out.println(byteBuffer.position());
        System.out.println("-------------------");
        byteBuffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));
        System.out.println(byteBuffer.position());
        System.out.println("-------------------");
        // 重置
        byteBuffer.reset();
        byteBuffer.get(bytes, 0 , 4);
        System.out.println(new String(bytes));
    }
}
