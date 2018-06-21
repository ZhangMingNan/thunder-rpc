package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Protocol;
import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultRpcProtocol implements Protocol {
    @Override
    public <T> Exporter<T> export(Provider<T> provider, URL url) {

        return null;
    }

    @Override
    public void destroy() {

    }
}
