package com.ly.zmn48644.rpc.spring;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ThunderBeanDefinitionParser implements BeanDefinitionParser {


    private Class<?> beanClass;

    public ThunderBeanDefinitionParser(Class beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String localName = element.getLocalName();
        if ("registry".equals(localName)) {
            return parseRegistry(element, parserContext);
        } else if ("service".equals(localName)) {
            return parseService(element, parserContext);
        } else if ("reference".equals(localName)) {
            return parseReference(element, parserContext);
        } else if ("protocol".equals(localName)) {
            return parseProtocol(element, parserContext);
        }
        return null;
    }

    private BeanDefinition parseProtocol(Element element, ParserContext parserContext) {
        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClass(beanClass);
        bd.setLazyInit(false);
        String id = element.getAttribute("id");
        //拿到协议名称
        String name = element.getAttribute("name");
        //拿到序列化方式
        String serialization = element.getAttribute("serialization");

        bd.getPropertyValues().addPropertyValue("name", name);
        bd.getPropertyValues().addPropertyValue("serialization", serialization);

        parserContext.getRegistry().registerBeanDefinition(id, bd);
        return bd;
    }

    private RootBeanDefinition parseRegistry(Element element, ParserContext parserContext) {
        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClass(beanClass);
        bd.setLazyInit(false);

        String host = element.getAttribute("host");
        String regProtocol = element.getAttribute("regProtocol");
        String id = element.getAttribute("id");
        String port = element.getAttribute("port");

        bd.getPropertyValues().addPropertyValue("host", host);
        bd.getPropertyValues().addPropertyValue("regProtocol", regProtocol);
        bd.getPropertyValues().addPropertyValue("port", port);
        ThunderNamespaceHandler.registryDefineNames.add(id);
        parserContext.getRegistry().registerBeanDefinition(id, bd);
        return bd;
    }

    private RootBeanDefinition parseService(Element element, ParserContext parserContext) {
        try {
            RootBeanDefinition bd = new RootBeanDefinition();
            bd.setBeanClass(beanClass);
            bd.setLazyInit(false);

            String serviceInterface = element.getAttribute("interface");
            String ref = element.getAttribute("ref");

            String appKey = element.getAttribute("appKey");
            String serverPort = element.getAttribute("serverPort");
            String timeout = element.getAttribute("timeout");
            String id = element.getAttribute("id");

            bd.getPropertyValues().addPropertyValue("serviceInterface", serviceInterface);
            bd.getPropertyValues().addPropertyValue("ref", new RuntimeBeanReference(ref));
            bd.getPropertyValues().addPropertyValue("appKey", appKey);
            bd.getPropertyValues().addPropertyValue("serverPort", serverPort);
            bd.getPropertyValues().addPropertyValue("timeout", timeout);

            //注册中心
            String registry = element.getAttribute("registry");
            if (registry != null && registry.length() > 0) {
                bd.getPropertyValues().addPropertyValue("registry", new RuntimeBeanReference(registry));
            }
            //协议
            String protocol = element.getAttribute("protocol");
            if (protocol != null && protocol.length() > 0) {
                bd.getPropertyValues().addPropertyValue("protocol", new RuntimeBeanReference(protocol));
            }


            parserContext.getRegistry().registerBeanDefinition(id, bd);
            return bd;
        } catch (Exception e) {
            throw new RuntimeException("ProviderFactoryBean配置错误:" + ExceptionUtils.getStackTrace(e));
        }
    }

    private RootBeanDefinition parseReference(Element element, ParserContext parserContext) {
        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClass(beanClass);
        bd.setLazyInit(false);
        String id = element.getAttribute("id");
        String service = element.getAttribute("interface");
        String timeout = element.getAttribute("timeout");
        String appKey = element.getAttribute("appKey");
        bd.getPropertyValues().addPropertyValue("appKey", appKey);
        bd.getPropertyValues().addPropertyValue("targetInterface", service);
        bd.getPropertyValues().addPropertyValue("timeout", timeout);
        parserContext.getRegistry().registerBeanDefinition(id, bd);
        return bd;
    }
}
