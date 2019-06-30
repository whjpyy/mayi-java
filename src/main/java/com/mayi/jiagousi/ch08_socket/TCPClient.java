package com.mayi.jiagousi.ch08_socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        System.out.println("TCP 客户端启动..");
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("这是一条来自客户端的消息".getBytes());
        byte[] readInfo = new byte[1024];
        int read = socket.getInputStream().read(readInfo);
        System.out.println("服务器返回：" + new String(readInfo));
        outputStream.close();
    }
}
