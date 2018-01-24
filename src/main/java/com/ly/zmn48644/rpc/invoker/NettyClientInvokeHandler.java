package com.ly.zmn48644.rpc.invoker;

import com.ly.zmn48644.rpc.model.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class NettyClientInvokeHandler extends SimpleChannelInboundHandler<RpcResponse> {


    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcResponse rpcResponse) throws Exception {

        InvokerResponseHolder.putResultValue(rpcResponse);
    }

}
