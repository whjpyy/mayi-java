package com.mayi.jiagousi.ch10_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String value = (String)msg;
        System.out.println("服务器接收到客户端的消息：" + value);
        ctx.writeAndFlush("ojbk");
    }
}

/**
 * netty5
 */
public class NettyServer2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("服务器端启动...");
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        NioEventLoopGroup cGroup = new NioEventLoopGroup();
        // 创建通道类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(pGroup, cGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ByteBuf byteBuf = Unpooled.copiedBuffer("_mayi".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });
        // 启动
        ChannelFuture channelFuture = serverBootstrap.bind(8080);
        channelFuture.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();

    }
}
