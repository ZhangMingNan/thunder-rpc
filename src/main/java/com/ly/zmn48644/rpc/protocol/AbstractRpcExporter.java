package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

public abstract class  AbstractRpcExporter<T> extends AbstractNode {
    private Provider<T> provider;
    @Override
    public Provider<T> getProvider() {
        return provider;
    }
}
