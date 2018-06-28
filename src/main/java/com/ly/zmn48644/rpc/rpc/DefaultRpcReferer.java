package com.ly.zmn48644.rpc.rpc;

import com.ly.zmn48644.rpc.protocol.AbstractReferer;
import com.ly.zmn48644.rpc.transport.Client;
import com.ly.zmn48644.rpc.transport.EndpointFactory;
import com.ly.zmn48644.rpc.transport.netty.NettyEndpointFactory;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public class DefaultRpcReferer extends AbstractReferer {


    public DefaultRpcReferer(URL url) {
        this.endpointFactory = new NettyEndpointFactory();
        this.client = endpointFactory.createClient(url);
    }

    private EndpointFactory endpointFactory;
    private Client client;

    @Override
    public Response doCall(Request request) {
        return client.request(request);
    }

    @Override
    public boolean doInit() {
        boolean result = client.open();
        return result;
    }
}