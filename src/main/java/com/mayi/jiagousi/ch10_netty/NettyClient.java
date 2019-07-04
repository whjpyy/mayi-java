package com.mayi.jiagousi.ch10_netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// netty客户端
public class NettyClient {


    public static void main(String[] args) {
        // 创建服务对象
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        // 创建2个线程池 1 监听端口号， 2 nio监听
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // 将线程放入工程
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boss, worker));
        // 设置管道工程
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                // 设置监听类
                pipeline.addLast("serverHandler", new SimpleChannelHandler(){
                    // 接受客户端数据
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        super.messageReceived(ctx, e);
                        System.out.println("服务器端向客户端数据：" + e.getMessage());
                    }

                    @Override
                    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
                        super.channelDisconnected(ctx, e);
                        System.out.println("channelDisconnected");
                    }

                    @Override
                    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
                        super.channelClosed(ctx, e);
                        System.out.println("channelClosed");
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
                        super.exceptionCaught(ctx, e);
                        System.out.println("exceptionCaught");
                    }
                });
                return pipeline;
            }
        });
        // 绑定端口号
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.println("客户端启动");
        Channel channel = connect.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入内容：");
            channel.write(scanner.next());
        }

    }
}
