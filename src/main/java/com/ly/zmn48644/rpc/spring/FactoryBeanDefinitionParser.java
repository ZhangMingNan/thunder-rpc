package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.invoker.InvokerFactoryBean;
import com.ly.zmn48644.rpc.provider.ProviderFactoryBean;
import com.ly.zmn48644.rpc.registry.RegistryFactoryBean;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class FactoryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final String REGISTRY_ELEMENT = "registry";
    private static final String SERVICE_ELEMENT = "service";
    private static final String REFERENCE_ELEMENT = "reference";
    private static final Map<String, Class<?>> factoryBeanMap = new HashMap<>(3);

    static {
        factoryBeanMap.put(REGISTRY_ELEMENT, RegistryFactoryBean.class);
        factoryBeanMap.put(SERVICE_ELEMENT, ProviderFactoryBean.class);
        factoryBeanMap.put(REFERENCE_ELEMENT, InvokerFactoryBean.class);
    }


    @Override
    protected Class<?> getBeanClass(Element element) {

        return factoryBeanMap.get(element.getLocalName());
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String localName = element.getLocalName();
        if ("registry".equals(localName)) {
            parseRegistry(element, builder);
        } else if ("service".equals(localName)) {
            parseService(element, builder);
        } else if ("reference".equals(localName)) {
            parseReference(element, builder);
        }

    }

    private void parseRegistry(Element element, BeanDefinitionBuilder builder) {
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        builder.addPropertyValue("host", host);
        builder.addPropertyValue("port", Integer.parseInt(port));
    }

    private void parseService(Element element, BeanDefinitionBuilder builder) {
        try {
            String serviceInterface = element.getAttribute("interface");
            String ref = element.getAttribute("ref");
            String appKey = element.getAttribute("appKey");

            String serverPort = element.getAttribute("serverPort");
            String timeout = element.getAttribute("timeout");

            builder.addPropertyValue("serviceInterface", Class.forName(serviceInterface));
            builder.addPropertyReference("serviceObject", ref);
            builder.addPropertyValue("appKey", appKey);
            builder.addPropertyValue("serverPort", Integer.parseInt(serverPort));
            builder.addPropertyValue("timeout", Integer.parseInt(timeout));
        } catch (Exception e) {
            throw new RuntimeException("ProviderFactoryBean配置错误:" + ExceptionUtils.getStackTrace(e));
        }
    }

    private void parseReference(Element element, BeanDefinitionBuilder builder) {
        String service = element.getAttribute("interface");
        String timeout = element.getAttribute("timeout");
        String appKey = element.getAttribute("appKey");
        try {
            builder.addPropertyValue("targetInterface", Class.forName(service));
            builder.addPropertyValue("timeout", Integer.valueOf(timeout));
            builder.addPropertyValue("appKey", appKey);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
