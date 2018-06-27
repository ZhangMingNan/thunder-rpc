package com.ly.zmn48644.rpc.protocol;


import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Protocol;
import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

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
