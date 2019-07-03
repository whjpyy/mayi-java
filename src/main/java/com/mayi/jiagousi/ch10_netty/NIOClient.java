package com.mayi.jiagousi.ch10_netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        System.out.println("客户端已经启动...");
        // 1.创建Socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(new Date().toString().getBytes());
        // 切换到读取模式
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        // 关闭通道
        socketChannel.close();
    }
}
