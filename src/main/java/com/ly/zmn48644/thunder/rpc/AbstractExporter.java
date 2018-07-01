package com.ly.zmn48644.thunder.rpc;


import com.ly.zmn48644.protocol.AbstractNode;

public abstract class AbstractExporter<T> extends AbstractNode implements Exporter<T>{
    private Provider<T> provider;
    @Override
    public Provider<T> getProvider() {
        return provider;
    }
}
