package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.config.ServiceConfig;
import com.ly.zmn48644.rpc.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ServiceConfigBean extends ServiceConfig implements FactoryBean, BeanFactoryAware, InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    public ServiceConfigBean() {

    }

    private BeanFactory beanFactory;

    public Object getObject() throws Exception {
        //工厂返回的代理对象
        return getRef();
    }

    public Class<?> getObjectType() {
        //当前工厂中返回的的bean的接口类型
        return interfaceClass;
    }

    public boolean isSingleton() {
        //ServiceConfigBean 是否是单例的
        return true;
    }


    /**
     * 当前工厂类的初始化方法
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {

        //解析注册中心配置
        checkAndConfigRegistry();

    }

    private void checkAndConfigRegistry() {
        //从容器中获取注册配置
        RegistryConfig registryConfig = beanFactory.getBean(RegistryConfig.class);
        if (registryConfig != null) {
            setRegistry(registryConfig);
        } else {
            throw new RuntimeException("必须配置注册中心!");
        }

    }


    public Class<?> getServiceInterface() {
        return interfaceClass;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.interfaceClass = serviceInterface;
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
