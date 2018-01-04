package com.ly.zmn48644.rpc.provider;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ProviderFactoryBean implements FactoryBean, InitializingBean {

    //服务接口
    private Class<?> serviceInterface;
    //服务实现
    private Object serviceObject;

    //服务被调用超时时间
    private int timeout;

    //服务启动端口
    private int serverPort;

    //服务提供者唯一标识
    private String appKey;

    //真实服务实现对象的代理对象
    private Object serviceProxyObject;

    public Object getObject() throws Exception {
        //工厂返回的代理对象
        return serviceProxyObject;
    }

    public Class<?> getObjectType() {
        //当前工厂中返回的的bean的接口类型
        return serviceInterface;
    }

    public boolean isSingleton() {
        //ProviderFactoryBean 是否是单例的
        return true;
    }


    /**
     * 当前工厂类的初始化方法
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        //初始化netty服务
        NettyServer.server().start(serverPort,serviceObject);


        System.out.println("服务提供者工厂创建完成!");
    }


    public Class<?> getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public Object getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(Object serviceObject) {
        this.serviceObject = serviceObject;
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

    public Object getServiceProxyObject() {
        return serviceProxyObject;
    }

    public void setServiceProxyObject(Object serviceProxyObject) {
        this.serviceProxyObject = serviceProxyObject;
    }

    @Override
    public String toString() {
        return "ProviderFactoryBean{" +
                "serviceInterface=" + serviceInterface +
                ", serviceObject=" + serviceObject +
                ", timeout=" + timeout +
                ", serverPort=" + serverPort +
                ", appKey='" + appKey + '\'' +
                ", serviceProxyObject=" + serviceProxyObject +
                '}';
    }
}
