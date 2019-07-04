package com.mayi.jiagousi.ch10_netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClient2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("客户端启动");
        NioEventLoopGroup pGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(pGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ByteBuf byteBuf = Unpooled.copiedBuffer("_mayi".getBytes());
                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new ChannelHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("客户端接收到的消息:" + msg);
                            }
                        });
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("我是真的服了。。_mayi".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("我是真的服了。。_mayi".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("我是真的服了。。_mayi".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("我是真的服了。。_mayi".getBytes()));
        channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("我是真的服了。。_mayi".getBytes()));
//        for (int i = 0; i < 20; i++) {
//            channelFuture.channel().write(Unpooled.wrappedBuffer("我是真的服了。。".getBytes()));
//            channelFuture.channel().write(Unpooled.wrappedBuffer("我是真的服了。。".getBytes()));
//        }
        channelFuture.channel().flush();
        // 等待客户端端口关闭
        channelFuture.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
    }
}
