package com.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * 服务端handler
 */
public class FirstHandler  extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端读到数据:" + byteBuf.toString(Charset.forName("utf-8")));
        // 回复数据到客户端
        System.out.println("服务端开始写出数据");
        // 创建需要回复的数据
        ByteBuf out = getByteBuf(ctx);
        // 发送数据
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "服务端往客服端写数据".getBytes(Charset.forName("utf-8"));
        // 申请一个数据结构存储信息
        ByteBuf buffer = ctx.alloc().buffer();
        // 将信息放入数据结构中
        buffer.writeBytes(bytes);
        return buffer;
    }
}
