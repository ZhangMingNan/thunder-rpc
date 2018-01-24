package com.ly.zmn48644.rpc.invoker;

import com.ly.zmn48644.rpc.invoker.proxy.InvokerInvocationHandler;
import com.ly.zmn48644.rpc.invoker.proxy.InvokerProxyBeanFactory;
import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class InvokerFactoryBean implements FactoryBean, BeanFactoryAware, InitializingBean {
    private Class<?> targetInterface;

    private Integer timeout;

    private String appKey;

    private Object proxyObject;

    private BeanFactory beanFactory;

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

        ZookeeperRegistry zookeeperRegistry = beanFactory.getBean(ZookeeperRegistry.class);

        Map<String, List<Provider>> providerMap = zookeeperRegistry.pullProvider();

        NettyChannelPoolFactory.instance().initChannelPoolFactory(providerMap);


        InvokerProxyBeanFactory proxyBeanFactory = new InvokerProxyBeanFactory();
        this.proxyObject = proxyBeanFactory.getProxy(targetInterface, new InvokerInvocationHandler(targetInterface,providerMap));
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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
