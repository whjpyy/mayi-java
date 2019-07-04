package com.mayi.jiagousi.ch10_netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * netty服务器端
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 创建服务对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 创建2个线程池 1 监听端口号， 2 nio监听
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        // 将线程放入工程
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));
        // 设置管道工程
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
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
                        System.out.println("客户端数据：" + e.getMessage());
                        ctx.getChannel().write("你好啊");
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
        serverBootstrap.bind(new InetSocketAddress(8080));
        System.out.println("服务器端已启动");
        while (true){
            Thread.sleep(5000);
            System.out.println("每5秒打印一次");
        }
    }
}
