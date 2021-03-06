package org.ming.thunder.protocol;


import org.ming.thunder.rpc.Exporter;
import org.ming.thunder.rpc.Protocol;
import org.ming.thunder.rpc.Provider;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.ProviderMessageRouter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class  DefaultRpcProtocol extends AbstractProtocol implements Protocol {

    private ConcurrentHashMap<String, ProviderMessageRouter> ipPort2RequestRouter = new ConcurrentHashMap<>();

    @Override
    protected <T> Exporter<T> createExporter(Provider<T> provider, URL url) {
        return new DefaultRpcExporter<T>(provider, url,this.ipPort2RequestRouter);
    }
}
