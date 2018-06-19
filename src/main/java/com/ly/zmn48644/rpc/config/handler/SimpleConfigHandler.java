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

        //暴露服务
        Protocol protocol = new DefaultRpcProtocol();
        Provider<T> provider = new DefaultProvider(interfaceClass, ref, registryUrl);
        Exporter<T> exporter = protocol.export(provider, registryUrl);

        //向注册中心注册服务


        return exporter;
    }

    @Override
    public <T> void unexport(List<Exporter<T>> exporters, URL registryUrl) {

    }
}
