package org.ming.thunder.protocol;


import org.ming.thunder.rpc.Exporter;
import org.ming.thunder.rpc.Protocol;
import org.ming.thunder.rpc.Provider;
import org.ming.thunder.rpc.URL;

import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractProtocol implements Protocol {

    protected ConcurrentHashMap<String, Exporter<?>> exporterMap = new ConcurrentHashMap<String, Exporter<?>>();

    @Override
    public <T> Exporter<T> export(Provider<T> provider, URL url) {

        Exporter<T> exporter = createExporter(provider, url);
        exporter.init();

        return exporter;
    }

    protected abstract <T> Exporter<T> createExporter(Provider<T> provider, URL url);

    @Override
    public void destroy() {

    }

}
