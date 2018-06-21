package com.ly.zmn48644.rpc.protocol;


import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.Protocol;
import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

public abstract class AbstractProtocol implements Protocol {

    @Override
    public <T> Exporter<T> export(Provider<T> provider, URL url) {

        return null;
    }

    protected abstract <T> Exporter<T> createExporter(Provider<T> provider, URL url);

    @Override
    public void destroy() {

    }

}
