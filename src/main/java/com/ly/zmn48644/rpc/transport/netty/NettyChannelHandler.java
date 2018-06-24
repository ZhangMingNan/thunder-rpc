package com.ly.zmn48644.rpc.transport.netty;

import com.ly.zmn48644.rpc.rpc.DefaultRequest;
import com.ly.zmn48644.rpc.rpc.DefaultResponse;
import com.ly.zmn48644.rpc.rpc.Request;
import com.ly.zmn48644.rpc.rpc.Response;
import com.ly.zmn48644.rpc.serialization.Serialization;
import com.ly.zmn48644.rpc.serialization.json.FastJsonSerialization;
import com.ly.zmn48644.rpc.transport.*;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

public class NettyChannelHandler extends ChannelDuplexHandler {

    private MessageHandler messageHandler;

    private Serialization serialization;

    public NettyChannelHandler(MessageHandler messageHandler) {
        //TODO 暂时硬编码
        serialization = new FastJsonSerialization();
        this.messageHandler = messageHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NettyMessage) {
            //处理message
            processMessage(ctx, NettyMessage.class.cast(msg));
        } else {
            throw new RuntimeException("NettyMessage type error!");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    /**
     * 处理网络消息
     *
     * @param ctx
     * @param message
     */
    private void processMessage(ChannelHandlerContext ctx, NettyMessage message) {
        //使用反序列化工具将二进制数据转换成对象.
        try {
            if (message.isRequest()) {
                //如果消息类型是请求
                Request request = serialization.deserialize(message.getData(), DefaultRequest.class);
                processRequest(ctx, request);
            } else {
                //如果消息类型是响应
                Response response = serialization.deserialize(message.getData(), DefaultResponse.class);
                processResponse(ctx, response);
            }
        } catch (IOException e) {
            //TODO 处理编解码过程中出现的异常.
            e.printStackTrace();
        }
    }

    private void processResponse(ChannelHandlerContext ctx, Response response) {
        messageHandler.handle(response);
    }

    private void processRequest(ChannelHandlerContext ctx, Request request) {
        Object result = messageHandler.handle(request);

        DefaultResponse response = null;
        if (result instanceof Response) {
            //说明抛出了异常
            response = DefaultResponse.class.cast(result);
        } else {
            response = new DefaultResponse(result);
        }
        response.setRequestId(request.getRequestId());

        sendResponse(ctx, response);
    }

    private void sendResponse(ChannelHandlerContext ctx, Response response) {
        if (ctx.channel().isActive()) {
            try {
                NettyMessage message = new NettyMessage(false, response.getRequestId(), serialization.serialize(response));
                ctx.channel().writeAndFlush(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
