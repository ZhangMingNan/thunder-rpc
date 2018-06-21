package com.ly.zmn48644.rpc.config.handler;

import com.ly.zmn48644.rpc.protocol.DefaultRpcProtocol;

import com.ly.zmn48644.rpc.rpc.*;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class SimpleConfigHandler implements ConfigHandler {

    @Override
    public <T> Exporter<T> export(Class<T> interfaceClass, T ref, URL registryUrl) {

        URL serviceUrl = null;
        //暴露服务
        Protocol protocol = new DefaultRpcProtocol();
        Provider<T> provider = new DefaultProvider(interfaceClass, ref, registryUrl);

        //启动传输层服务器
        Exporter<T> exporter = protocol.export(provider, serviceUrl);

        //向注册中心注册
        register(registryUrl, serviceUrl);

        return exporter;
    }

    /**
     * 行注册中心注册服务
     *
     * @param registryUrl
     * @param serviceUrl
     */
    private void register(URL registryUrl, URL serviceUrl) {

    }

    @Override
    public <T> void unexport(List<Exporter<T>> exporters, URL registryUrl) {

    }
}