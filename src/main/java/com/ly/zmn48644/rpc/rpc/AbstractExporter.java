package com.ly.zmn48644.rpc.rpc;

import com.ly.zmn48644.rpc.protocol.AbstractNode;
import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

public abstract class AbstractExporter<T> extends AbstractNode implements Exporter<T>{
    private Provider<T> provider;
    @Override
    public Provider<T> getProvider() {
        return provider;
    }
}
