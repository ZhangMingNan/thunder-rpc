package com.ly.zmn48644.rpc.config.handler;

import com.ly.zmn48644.rpc.common.URLParamType;
import com.ly.zmn48644.rpc.protocol.DefaultRpcProtocol;

import com.ly.zmn48644.rpc.rpc.*;
import com.ly.zmn48644.rpc.utils.StringTools;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class SimpleConfigHandler implements ConfigHandler {

    @Override
    public <T> Exporter<T> export(Class<T> interfaceClass, T ref, URL registryUrl) {


//        NettyServer.server().start(getServerPort(), getRef());
        //向注册中心注册服务
//        ZookeeperRegistry zookeeperRegistry = beanFactory.getBean(ZookeeperRegistry.class);
//        InetAddress localAddress = NetUtils.getLocalAddress();
//        Provider provider = new Provider(serviceInterface.getName(), localAddress.getHostAddress(), getServerPort());
//        zookeeperRegistry.registerProvider(provider);
//        System.out.println("注册服务："+provider.getService()+","+provider.getHost()+","+provider.getPort()+","+provider.getClass());

        //TODO 将 registryUrl 转换为 serviceUrl!
        String serviceStr = StringTools.urlDecode(registryUrl.getParameter(URLParamType.embed.getName()));
        URL serviceUrl = URL.valueOf(serviceStr);
        //暴露服务
        Protocol protocol = new DefaultRpcProtocol();
        Provider<T> provider = new DefaultProvider(interfaceClass, ref, serviceUrl);

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
