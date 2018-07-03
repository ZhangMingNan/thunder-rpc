package com.ly.zmn48644.thunder.rpc;


import com.ly.zmn48644.thunder.extension.ExtensionLoader;
import com.ly.zmn48644.thunder.protocol.AbstractReferer;
import com.ly.zmn48644.thunder.transport.Client;
import com.ly.zmn48644.thunder.transport.EndpointFactory;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public class DefaultRpcReferer extends AbstractReferer {

    public DefaultRpcReferer(URL url) {
        super(url);
    }

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
