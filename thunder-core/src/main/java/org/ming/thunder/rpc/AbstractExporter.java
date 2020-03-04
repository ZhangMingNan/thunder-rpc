package org.ming.thunder.rpc;


import org.ming.thunder.protocol.AbstractNode;

public abstract class AbstractExporter<T> extends AbstractNode implements Exporter<T>{
    private Provider<T> provider;
    @Override
    public Provider<T> getProvider() {
        return provider;
    }
}
