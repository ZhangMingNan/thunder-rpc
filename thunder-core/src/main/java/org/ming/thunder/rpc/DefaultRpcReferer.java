package org.ming.thunder.rpc;


import org.ming.thunder.protocol.AbstractReferer;

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
