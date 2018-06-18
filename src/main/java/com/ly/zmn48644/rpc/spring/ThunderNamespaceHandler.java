package com.ly.zmn48644.rpc.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ThunderNamespaceHandler extends NamespaceHandlerSupport {


    public void init() {
        registerBeanDefinitionParser("registry", new ThunderBeanDefinitionParser(RegistryConfigBean.class));
        registerBeanDefinitionParser("service", new ThunderBeanDefinitionParser(ProviderConfigBean.class));
        registerBeanDefinitionParser("reference", new ThunderBeanDefinitionParser(InvokerConfigBean.class));
    }
}
