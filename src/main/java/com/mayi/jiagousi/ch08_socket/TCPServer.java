package com.mayi.jiagousi.ch08_socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        System.out.println("tcp协议服务器启动..");
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int length = inputStream.read(bytes);
                    String result = new String(bytes, 0, length);
                    System.out.println("服务器端接受客户端内容：" + result);
                    socket.getOutputStream().write("ok..".getBytes());
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }
    }
}
