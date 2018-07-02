package com.ly.zmn48644.thunder.transport.netty4;


import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.thunder.transport.Client;
import com.ly.zmn48644.thunder.transport.EndpointFactory;
import com.ly.zmn48644.thunder.transport.MessageHandler;
import com.ly.zmn48644.thunder.transport.Server;


/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
@SpiMeta(name = "netty")
public class NettyEndpointFactory implements EndpointFactory {


    //TODO 暂时不缓存 server .
    @Override
    public Server createServer(URL url, MessageHandler messageHandler) {
        return new Netty4Server(url, messageHandler);
    }

    //TODO 暂时不缓存 client .
    @Override
    public Client createClient(URL url) {
        return new Netty4Client(url);
    }
}
