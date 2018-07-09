package com.ly.zmn48644.thunder.transport.netty4;


import com.ly.zmn48644.thunder.extension.ExtensionLoader;
import com.ly.zmn48644.thunder.rpc.DefaultRequest;
import com.ly.zmn48644.thunder.rpc.DefaultResponse;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;
import com.ly.zmn48644.thunder.serialization.Serialization;
import com.ly.zmn48644.thunder.transport.MessageHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.util.concurrent.Executor;

public class NettyChannelHandler extends ChannelDuplexHandler {

    private MessageHandler messageHandler;

    private Serialization serialization;


    private Executor executor;

    public NettyChannelHandler(MessageHandler messageHandler, Executor executor) {
        //TODO 暂时硬编码, 参数应该从 url 中获取
        this.serialization = ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("hessian");
        this.messageHandler = messageHandler;
        this.executor = executor;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NettyMessage) {
            //处理message
            //在线程池中处理
            executor.execute(() -> processMessage(ctx, NettyMessage.class.cast(msg)));
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
