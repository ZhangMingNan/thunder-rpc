package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistryFactory;
import com.ly.zmn48644.rpc.utils.NetUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ProviderFactoryBean implements FactoryBean,BeanFactoryAware, InitializingBean {

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

    private BeanFactory beanFactory;

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

        ZookeeperRegistry zookeeperRegistry = beanFactory.getBean(ZookeeperRegistry.class);


        List<Provider> providerList = new LinkedList<>();
        InetAddress localAddress = NetUtils.getLocalAddress();
        Provider provider = new Provider(serviceInterface.getName(),localAddress.getHostAddress(),serverPort);

        zookeeperRegistry.registerProvider(providerList);

        System.out.println("服务端地址:"+localAddress.toString());


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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
