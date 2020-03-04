package org.ming.thunder.transport;


import org.ming.thunder.rpc.URL;

/**
 * 作者：张明楠
 * 时间：2018/6/25
 */
public interface EndpointFactory {

    Server createServer(URL url, MessageHandler messageHandler);

    Client createClient(URL url);
}
