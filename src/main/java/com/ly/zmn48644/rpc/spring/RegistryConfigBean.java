package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.config.RegistryConfig;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistryFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


public class RegistryConfigBean extends RegistryConfig implements FactoryBean, InitializingBean {

    private ZookeeperRegistry registry;

    @Override
    public Object getObject() throws Exception {
        return registry;
    }

    @Override
    public Class<?> getObjectType() {
        return ZookeeperRegistry.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ZookeeperRegistryFactory registryFactory = new ZookeeperRegistryFactory();
        this.registry = registryFactory.build(getHost(), getPort());
    }
}
