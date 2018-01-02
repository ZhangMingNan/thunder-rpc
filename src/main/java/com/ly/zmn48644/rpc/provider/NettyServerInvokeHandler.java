package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.model.RpcRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerInvokeHandler extends SimpleChannelInboundHandler<RpcRequest> {
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




        }

    }
}
