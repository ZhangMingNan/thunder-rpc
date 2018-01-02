package com.ly.zmn48644.rpc.revoker;

import com.ly.zmn48644.rpc.revoker.proxy.RevokerInvocationHandler;
import com.ly.zmn48644.rpc.revoker.proxy.RevokerProxyBeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class RevokerFactoryBean implements FactoryBean, InitializingBean {
    private Class<?> targetInterface;

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

        RevokerProxyBeanFactory proxyBeanFactory = new RevokerProxyBeanFactory();
        this.proxyObject = proxyBeanFactory.getProxy(targetInterface, new RevokerInvocationHandler(targetInterface));
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
}
