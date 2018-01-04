package com.ly.zmn48644.rpc.revoker;

import com.ly.zmn48644.rpc.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ArrayBlockingQueue;

public class NettyClientInvokeHandler extends SimpleChannelInboundHandler<RpcResponse> {

    private ArrayBlockingQueue<RpcResponse> queue;
    public NettyClientInvokeHandler(ArrayBlockingQueue<RpcResponse> queue){
        this.queue = queue;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {

        queue.add(rpcResponse);
        System.out.println(rpcResponse.toString());
    }

}
