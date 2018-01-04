package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.model.RpcRequest;
import com.ly.zmn48644.rpc.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.reflect.MethodUtils;

public class NettyServerInvokeHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private Object serviceObject;
    public NettyServerInvokeHandler(Object serviceObject){
        this.serviceObject = serviceObject;
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常,关闭链路
        ctx.close();
    }
    @Override
    public void channelRead0(ChannelHandlerContext context, RpcRequest rpcRequest) throws Exception {
        //如果可读
        if (context.channel().isWritable()){
            //首先要能够接收到 rpcRequest
            RpcResponse response = new RpcResponse();
            response.setResult(MethodUtils.invokeMethod(serviceObject,rpcRequest.getMethod(),rpcRequest.getObjects()));
            context.writeAndFlush(response);

        }

    }
}
