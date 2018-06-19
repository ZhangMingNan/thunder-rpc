package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.config.ProviderConfig;
import com.ly.zmn48644.rpc.provider.NettyServer;
import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistryFactory;
import com.ly.zmn48644.rpc.utils.NetUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.net.InetAddress;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ProviderConfigBean extends ProviderConfig implements FactoryBean, BeanFactoryAware, InitializingBean ,ApplicationListener<ContextRefreshedEvent>{
    public ProviderConfigBean() {

        System.out.println("创建了：ProviderConfigBean");
    }
    //服务接口
    private Class<?> serviceInterface;



    private BeanFactory beanFactory;

    public Object getObject() throws Exception {
        //工厂返回的代理对象
        return getRef();
    }

    public Class<?> getObjectType() {
        //当前工厂中返回的的bean的接口类型
        return serviceInterface;
    }

    public boolean isSingleton() {
        //ProviderConfigBean 是否是单例的
        return true;
    }


    /**
     * 当前工厂类的初始化方法
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {

    }


    public Class<?> getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //容器创建完成,开始暴露服务。
        export();
    }

}
