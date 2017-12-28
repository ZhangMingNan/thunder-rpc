package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.provider.ProviderFactoryBean;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class ProviderFactoryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return ProviderFactoryBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        //从配置文件中获取配置
        try {
            String serviceInterface = element.getAttribute("interface");
            String ref = element.getAttribute("ref");
            String appKey = element.getAttribute("appKey");

            String serverPort = element.getAttribute("serverPort");
            String timeout = element.getAttribute("timeout");

            builder.addPropertyValue("serviceInterface", Class.forName(serviceInterface));
            builder.addPropertyValue("serviceObject", ref);
            builder.addPropertyValue("appKey", appKey);
            builder.addPropertyValue("serverPort", Integer.parseInt(serverPort));
            builder.addPropertyValue("timeout", Integer.parseInt(timeout));
        } catch (Exception e) {
            throw new RuntimeException("ProviderFactoryBean配置错误:"+ ExceptionUtils.getStackTrace(e));
        }
    }
}
