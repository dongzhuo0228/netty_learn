package com.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    //主线程
    public static void main(String[] args) throws Exception {
        // 监听端口，接收新连接的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理每一条新连接数据读写的线程组
        // 形象一点就是说boss负责接活，worker负责干活
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 配置引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 配置两个线程组
                .group(bossGroup, workerGroup)
                // 指定所使用的NIO传输的channel
                .channel(NioServerSocketChannel.class)
                // 给引导类创建一个ChannelInitializer，当一个新的连接创建时，一个系的子Channel将会被创建，而ChannelInitializer将会把一个你的handler添加到该Channel的ChannelPipeline中
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // 这里将会绑定一个handler来处理业务逻辑,暂时先不指定hanler
                        ch.pipeline().addLast(new FirstHandler());
                    }
                })
                .bind(7878);
    }
}
