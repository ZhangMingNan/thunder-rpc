package com.ly.zmn48644.rpc.transport.netty;

import com.ly.zmn48644.rpc.rpc.URL;
import com.ly.zmn48644.rpc.transport.Client;
import com.ly.zmn48644.rpc.transport.EndpointFactory;
import com.ly.zmn48644.rpc.transport.MessageHandler;
import com.ly.zmn48644.rpc.transport.Server;

/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
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
