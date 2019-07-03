package com.mayi.jiagousi.ch10_netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器已经启动");
        // 创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定链接
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器中，并且监听已经接受到的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮训获取“已经准备就绪”事件
        while(selector.select() > 0){
            // 获取当前选择器有注册已经监听到的数据
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                // 获取准备就绪事件
                SelectionKey sk = it.next();
                // 判断是否接受到客户端信息，注册到选择器中
                if(sk.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    // 读取数据
                    int len = 0;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    while ((len=socketChannel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, len));
                        byteBuffer.clear();
                    }
                }
                it.remove();
            }

        }
    }
}
