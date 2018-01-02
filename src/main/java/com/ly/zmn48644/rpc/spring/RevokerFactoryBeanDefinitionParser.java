package com.ly.zmn48644.rpc.spring;

import com.ly.zmn48644.rpc.revoker.RevokerFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class RevokerFactoryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    @Override
    protected Class<?> getBeanClass(Element element) {
        return RevokerFactoryBean.class;
    }
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        String service = element.getAttribute("interface");
        try {
            builder.addPropertyValue("targetInterface", Class.forName(service));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
