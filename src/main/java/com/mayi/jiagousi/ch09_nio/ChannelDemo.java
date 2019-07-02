package com.mayi.jiagousi.ch09_nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelDemo {

    // 非直接缓冲区
    public static void test1() throws IOException {
        String path = "src/main/resources/";
        FileInputStream fis = new FileInputStream(path + "1.jpg");
        FileOutputStream fos = new FileOutputStream(path + "2.jpg");

        // 创建通道
        FileChannel cIn = fis.getChannel();
        FileChannel cOut = fos.getChannel();
        // 分配缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (cIn.read(byteBuffer) != -1){
            // 开启读模式
            byteBuffer.flip();
            // 将数据写入通道中
            cOut.write(byteBuffer);
            byteBuffer.clear();
        }
        // 关闭通道
        cIn.close();
        cOut.close();
        fis.close();
        fos.close();
    }

    // 直接缓冲区
    public static void test2() throws IOException {
        String path = "src/main/resources/";
        // 创建管道
        FileChannel inChannel = FileChannel.open(Paths.get(path + "1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(path + "2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.WRITE);
        // 定义映射文件
        MappedByteBuffer mapIn = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer mapOut = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        // 直接对缓冲区进行操作
        byte[] bytes = new byte[mapIn.limit()];
        mapIn.get(bytes);
        mapOut.put(bytes);
        inChannel.close();
        outChannel.close();
        System.out.println("操作直接缓冲区完毕");

    }

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        test1();        // 16
//        test2();      // 15
        System.out.println("耗时" + (System.currentTimeMillis() - start));
    }
}
