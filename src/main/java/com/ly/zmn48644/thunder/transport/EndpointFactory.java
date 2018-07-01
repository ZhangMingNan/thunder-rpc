package com.ly.zmn48644.thunder.transport;

import com.ly.zmn48644.rpc.URL;
import com.ly.zmn48644.rpc.URL;
import com.ly.zmn48644.thunder.rpc.URL;

/**
 * 作者：张明楠
 * 时间：2018/6/25
 */
public interface EndpointFactory {

    Server createServer(URL url, MessageHandler messageHandler);

    Client createClient(URL url);
}
