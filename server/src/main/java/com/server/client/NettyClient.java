package com.server.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) {
        // 线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 客户端配置引导类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程组
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                })
                // 连接本机8080端口
                .connect("127.0.0.1", 7878);
    }
}
