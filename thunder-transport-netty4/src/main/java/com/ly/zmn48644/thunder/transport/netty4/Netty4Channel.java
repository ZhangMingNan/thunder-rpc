package com.ly.zmn48644.thunder.transport.netty4;


import com.ly.zmn48644.thunder.extension.ExtensionLoader;
import com.ly.zmn48644.thunder.rpc.DefaultResponseFuture;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;
import com.ly.zmn48644.thunder.serialization.Serialization;

import com.ly.zmn48644.thunder.transport.Channel;
import io.netty.channel.ChannelFuture;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public class Netty4Channel implements Channel {

    private Netty4Client netty4Client;
    //Netty的 channel
    private io.netty.channel.Channel channel;

    private Serialization serialization;

    private InetSocketAddress remoteAddress;

    public Netty4Channel(Netty4Client netty4Client) {
        this.netty4Client = netty4Client;
        this.remoteAddress = new InetSocketAddress(netty4Client.getUrl().getHost(), netty4Client.getUrl().getPort());
        //TODO 临时这样处理
        this.serialization = ExtensionLoader.getExtensionLoader(Serialization.class).getExtension("hessian");
    }

    @Override
    public boolean open() {
        ChannelFuture channelFuture = this.netty4Client.getBootstrap().connect(remoteAddress);
        this.channel = channelFuture.channel();
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public Response request(Request request) {
        NettyMessage message = null;
        try {
            DefaultResponseFuture response = new DefaultResponseFuture(request, 30000);
            netty4Client.registerCallback(request.getRequestId(), response);
            message = new NettyMessage(true, request.getRequestId(), serialization.serialize(request));
            ChannelFuture channelFuture = channel.writeAndFlush(message);
            boolean result = channelFuture.awaitUninterruptibly(30000L, TimeUnit.MILLISECONDS);
            if (result && channelFuture.isSuccess()) {

                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
