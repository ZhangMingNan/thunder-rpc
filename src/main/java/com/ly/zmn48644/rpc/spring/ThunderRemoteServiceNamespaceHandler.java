package com.ly.zmn48644.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ThunderRemoteServiceNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("service", new ProviderFactoryBeanDefinitionParser());
    }
}
