package com.ly.zmn48644.thunder.transport.netty;


import com.ly.zmn48644.thunder.transport.SharedObjectFactory;

/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public class NettySharedObjectFactory implements SharedObjectFactory<Netty4Channel> {

    private Netty4Client netty4Client;

    public NettySharedObjectFactory(Netty4Client netty4Client) {
        this.netty4Client = netty4Client;
    }

    @Override
    public Netty4Channel makeObject() {
        return new Netty4Channel(netty4Client);
    }
}
