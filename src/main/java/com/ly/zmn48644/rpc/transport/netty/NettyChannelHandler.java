package com.ly.zmn48644.rpc.transport.netty;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

public class NettyChannelHandler extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NettyMessage) {
            //处理message
            processMessage(ctx, NettyMessage.class.cast(msg));
        } else {
            throw new RuntimeException("NettyMessage type error!");
        }
    }

    /**
     * 处理网络消息
     *
     * @param ctx
     * @param message
     */
    private void processMessage(ChannelHandlerContext ctx, NettyMessage message) {
        if (message.isRequest()) {
            //如果消息类型是请求
            processRequest(message.getData());
        } else {
            //如果消息类型是响应
            processResponse(message.getData());
        }
    }

    private void processResponse(byte[] data) {

    }

    private void processRequest(byte[] data) {

    }
}
