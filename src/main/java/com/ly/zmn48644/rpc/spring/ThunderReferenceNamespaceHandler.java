package com.ly.zmn48644.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ThunderReferenceNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("reference", new RevokerFactoryBeanDefinitionParser());
    }
}
