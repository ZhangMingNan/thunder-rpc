package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.*;
import com.ly.zmn48644.rpc.transport.ProviderMessageRouter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class  DefaultRpcProtocol extends AbstractProtocol implements Protocol {

    private ConcurrentHashMap<String, ProviderMessageRouter> ipPort2RequestRouter = new ConcurrentHashMap<>();

    @Override
    protected <T> Exporter<T> createExporter(Provider<T> provider, URL url) {
        return new DefaultRpcExporter<>(provider, url,this.ipPort2RequestRouter);
    }
}
