package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.*;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultRpcProtocol extends AbstractProtocol implements Protocol {

    @Override
    protected <T> Exporter<T> createExporter(Provider<T> provider, URL url) {
        return new DefaultRpcExporter<>(provider, url);
    }
}
