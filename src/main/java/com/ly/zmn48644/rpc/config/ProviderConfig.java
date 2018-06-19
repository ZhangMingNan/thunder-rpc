package com.ly.zmn48644.rpc.config;

import com.ly.zmn48644.rpc.provider.NettyServer;
import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.utils.NetUtils;

import java.net.InetAddress;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class ProviderConfig<T> {
    //服务被调用超时时间
    private int timeout;
    //服务启动端口
    private int serverPort;
    //服务提供者唯一标识
    private String appKey;

    private T ref;


    protected void export(){

        //启动server
        NettyServer.server().start(getServerPort(), getRef());

        //向注册中心注册服务
//        ZookeeperRegistry zookeeperRegistry = beanFactory.getBean(ZookeeperRegistry.class);
//        InetAddress localAddress = NetUtils.getLocalAddress();
//        Provider provider = new Provider(serviceInterface.getName(), localAddress.getHostAddress(), getServerPort());
//        zookeeperRegistry.registerProvider(provider);
//        System.out.println("注册服务："+provider.getService()+","+provider.getHost()+","+provider.getPort()+","+provider.getClass());
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
