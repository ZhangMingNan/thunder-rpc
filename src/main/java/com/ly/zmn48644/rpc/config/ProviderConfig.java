package com.ly.zmn48644.rpc.config;

import com.ly.zmn48644.rpc.provider.NettyServer;
import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.rpc.URL;
import com.ly.zmn48644.rpc.utils.NetUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class ProviderConfig<T> extends AbstractInterfaceConfig {
    //服务被调用超时时间
    private int timeout;
    //服务启动端口
    private int serverPort;
    //服务提供者唯一标识
    private String appKey;

    private T ref;

    protected void export() {

        URL registryUrl = loadRegistryUrl();





//        //启动server
//        NettyServer.server().start(getServerPort(), getRef());

        //向注册中心注册服务
//        ZookeeperRegistry zookeeperRegistry = beanFactory.getBean(ZookeeperRegistry.class);
//        InetAddress localAddress = NetUtils.getLocalAddress();
//        Provider provider = new Provider(serviceInterface.getName(), localAddress.getHostAddress(), getServerPort());
//        zookeeperRegistry.registerProvider(provider);
//        System.out.println("注册服务："+provider.getService()+","+provider.getHost()+","+provider.getPort()+","+provider.getClass());
    }

    private void doExport(ProtocolConfig protocolConfig, int port, List<URL> registryURLs) {

    }

    private URL loadRegistryUrl() {
        String protocol = this.registry.getRegProtocol();
        String host = this.registry.getHost();
        int port = this.registry.getPort();
        URL registryUrl = new URL(protocol,host,port,null,null);
        return registryUrl;
    }


    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {

        this.ref = ref;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
