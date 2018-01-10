package com.ly.zmn48644.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ThunderNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("registry", new FactoryBeanDefinitionParser());
        registerBeanDefinitionParser("service", new FactoryBeanDefinitionParser());
        registerBeanDefinitionParser("reference", new FactoryBeanDefinitionParser());
    }
}
