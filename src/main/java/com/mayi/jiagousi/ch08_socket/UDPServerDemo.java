package com.mayi.jiagousi.ch08_socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerDemo {

    public static void main(String[] args) throws IOException {
        System.out.println("UDP服务准备启动");
        DatagramSocket server = new DatagramSocket(8080);
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        server.receive(dp);
        System.out.println("来源IP地址：" + dp.getAddress() + ", 端口号：" + dp.getPort());
        String result = new String(dp.getData(), 0, dp.getLength());
        System.out.println(result);
        server.close();
    }
}
