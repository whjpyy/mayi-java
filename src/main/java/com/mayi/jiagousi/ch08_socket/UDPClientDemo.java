package com.mayi.jiagousi.ch08_socket;

import java.io.IOException;
import java.net.*;

public class UDPClientDemo {

    public static void main(String[] args) throws IOException {
        System.out.println("UDP客户端启动链接");
        DatagramSocket client = new DatagramSocket();
        String str = "客户端发送消息";
        byte[] bytes = str.getBytes();
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8080);
        client.send(dp);
        client.close();
    }
}
