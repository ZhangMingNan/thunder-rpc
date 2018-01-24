package com.ly.zmn48644.rpc.registry;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class RegistryFactoryBean implements FactoryBean, InitializingBean {
    private String host;
    private int port;
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
        this.registry = registryFactory.build(host,port);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
