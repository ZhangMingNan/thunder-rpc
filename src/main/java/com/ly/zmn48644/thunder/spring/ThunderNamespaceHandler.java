package com.ly.zmn48644.thunder.spring;


import com.ly.zmn48644.config.ProtocolConfig;
import com.ly.zmn48644.config.RegistryConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import java.util.HashSet;
import java.util.Set;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ThunderNamespaceHandler extends NamespaceHandlerSupport {
    public final static Set<String> registryDefineNames = new HashSet<>();
    public void init() {
        registerBeanDefinitionParser("protocol", new ThunderBeanDefinitionParser(ProtocolConfig.class));
        registerBeanDefinitionParser("registry", new ThunderBeanDefinitionParser(RegistryConfig.class));
        registerBeanDefinitionParser("service", new ThunderBeanDefinitionParser(ServiceConfigBean.class));
        registerBeanDefinitionParser("referer", new ThunderBeanDefinitionParser(RefererConfigBean.class));
    }
}
