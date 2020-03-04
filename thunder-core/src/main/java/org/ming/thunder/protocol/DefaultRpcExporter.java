package org.ming.thunder.protocol;

import org.ming.thunder.extension.ExtensionLoader;
import org.ming.thunder.rpc.AbstractExporter;
import org.ming.thunder.rpc.Provider;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.EndpointFactory;
import org.ming.thunder.transport.ProviderMessageRouter;
import org.ming.thunder.transport.Server;


import java.util.concurrent.ConcurrentHashMap;


/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultRpcExporter<T> extends AbstractExporter {


    protected Server server;
    protected EndpointFactory endpointFactory;
    protected Provider<T> provider;
    ConcurrentHashMap<String, ProviderMessageRouter> ipPort2RequestRouter;

    public DefaultRpcExporter(Provider<T> provider, URL url, ConcurrentHashMap<String, ProviderMessageRouter> ipPort2RequestRouter) {
        this.ipPort2RequestRouter = ipPort2RequestRouter;
        this.provider = provider;
        //TODO 临时这样处理.
        this.endpointFactory = ExtensionLoader.getExtensionLoader(EndpointFactory.class).getExtension("netty");
        ProviderMessageRouter messageRouter = initRequestRouter(url);
        this.server = endpointFactory.createServer(url, messageRouter);
    }


    /**
     * 初始化或者从缓存中获取router,并且向router 中添加 provider.
     *
     * @param url
     * @return
     */
    private ProviderMessageRouter initRequestRouter(URL url) {

        String ipPort = url.getServerPortStr();

        ProviderMessageRouter messageRouter = ipPort2RequestRouter.get(ipPort);

        if (messageRouter == null) {
            messageRouter = new ProviderMessageRouter();
            ipPort2RequestRouter.putIfAbsent(ipPort, messageRouter);
        }
        messageRouter.addProvider(provider);
        return messageRouter;
    }

    @Override
    public void unexport() {

    }

    @Override
    protected boolean doInit() {
        //启动服务
        return server.open();
    }

}
