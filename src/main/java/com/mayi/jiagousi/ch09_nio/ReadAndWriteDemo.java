package com.mayi.jiagousi.ch09_nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadAndWriteDemo {

    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/";
        // 随机访问
        RandomAccessFile raf = new RandomAccessFile(path + "text.txt", "rw");
        // 获取通道
        FileChannel channel = raf.getChannel();
        // 分配指定大小
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        // 分散读取
        ByteBuffer[] bufs = {buffer1, buffer2};
        channel.read(bufs);
        for (ByteBuffer item : bufs){
            // 切换成读模式
            item.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("-------------聚集读取--------------");
        RandomAccessFile raf2 = new RandomAccessFile(path + "text2.txt", "rw");
        // 获取通道
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);
        raf.close();
        raf2.close();
    }
}
