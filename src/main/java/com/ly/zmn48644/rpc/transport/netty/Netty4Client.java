package com.ly.zmn48644.rpc.transport.netty;

import com.ly.zmn48644.rpc.transport.AbstractClient;
import com.ly.zmn48644.rpc.transport.Request;
import com.ly.zmn48644.rpc.transport.Response;

public class Netty4Client extends AbstractClient {
    @Override
    public boolean open() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Response request(Request request) {
        return null;
    }
}
