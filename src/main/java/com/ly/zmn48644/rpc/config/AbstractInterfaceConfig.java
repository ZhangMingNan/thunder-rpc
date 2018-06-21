package com.ly.zmn48644.rpc.config;

public class AbstractInterfaceConfig {
    // 对应的注册中心的配置
    protected RegistryConfig registry;
    protected ProtocolConfig protocol;

    public void setRegistry(RegistryConfig registryConfig) {
        this.registry = registryConfig;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }


}
