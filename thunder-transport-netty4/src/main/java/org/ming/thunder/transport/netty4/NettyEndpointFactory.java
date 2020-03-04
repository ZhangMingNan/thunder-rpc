package org.ming.thunder.transport.netty4;


import org.ming.thunder.extension.SpiMeta;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.Client;
import org.ming.thunder.transport.EndpointFactory;
import org.ming.thunder.transport.MessageHandler;
import org.ming.thunder.transport.Server;


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
