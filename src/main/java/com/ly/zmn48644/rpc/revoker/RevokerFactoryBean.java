package com.ly.zmn48644.rpc.revoker;

import com.ly.zmn48644.rpc.revoker.proxy.RevokerInvocationHandler;
import com.ly.zmn48644.rpc.revoker.proxy.RevokerProxyBeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class RevokerFactoryBean implements FactoryBean, InitializingBean {
    private Class<?> targetInterface;

    private Integer timeout;

    private String appKey;

    private Object proxyObject;


    public Object getObject() throws Exception {
        return proxyObject;
    }

    public Class<?> getObjectType() {
        return targetInterface;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {

        //临时固定后端服务地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8099);

        RevokerProxyBeanFactory proxyBeanFactory = new RevokerProxyBeanFactory();
        this.proxyObject = proxyBeanFactory.getProxy(targetInterface, new RevokerInvocationHandler(targetInterface,address));
    }

    public Class<?> getTargetInterface() {
        return targetInterface;
    }

    public void setTargetInterface(Class<?> targetInterface) {
        this.targetInterface = targetInterface;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
