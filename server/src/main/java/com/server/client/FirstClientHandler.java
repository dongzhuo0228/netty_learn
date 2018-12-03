package com.server.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端开始写出数据");
        // 1.创建将要写出的数据
        ByteBuf buffer = getByteBuf(ctx);
        // 2.发送数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // byte类型的数据
        byte[] bytes = "客户端往服务端写数据".getBytes(Charset.forName("utf-8"));
        // 申请一个数据结构存储信息
        ByteBuf buffer = ctx.alloc().buffer();
        // 将信息放入数据结构中
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端读到数据：" + byteBuf.toString(Charset.forName("utf-8")));
    }
}
