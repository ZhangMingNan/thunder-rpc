package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Provider;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultRpcExporter<T> implements Exporter<T> {

    @Override
    public Provider<T> getProvider() {
        return null;
    }

    @Override
    public void unexport() {

    }
}
