package org.ming.thunder.spring;

import org.ming.thunder.config.ProtocolConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
        return parse(element, parserContext, beanClass);
    }

    private RootBeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {

        RootBeanDefinition bd = new RootBeanDefinition();
        bd.setBeanClass(beanClass);
        bd.setLazyInit(false);

        String id = element.getAttribute("id");
        if (StringUtils.isBlank(id)) {
            String generatedBeanName = element.getAttribute("name");
            if (generatedBeanName!=null){
                id = generatedBeanName;
            }
            if (StringUtils.isBlank(generatedBeanName)){
                id = beanClass.getName();
            }
            int counter = 1;
            while (parserContext.getRegistry().containsBeanDefinition(id)) {
                //防止覆盖
                id = beanClass.getName() + (counter++);
            }
        }

        for (Method method : this.beanClass.getMethods()) {
            String name = method.getName();
            // 必须是setXXX
            if (name.length() <= 3 || !name.startsWith("set") || !Modifier.isPublic(method.getModifiers())
                    || method.getParameterTypes().length != 1) {
                continue;
            }
            String property = (name.substring(3, 4).toLowerCase() + name.substring(4)).replaceAll("_", "-");
            String value = element.getAttribute(property);
            if (StringUtils.isBlank(value)) {
                continue;
            }

            if ("registry".equals(property)) {
                String registry = element.getAttribute("registry");
                if (StringUtils.isNotBlank(registry)) {
                    bd.getPropertyValues().addPropertyValue("registry", new RuntimeBeanReference(registry));
                }
            } else if ("protocol".equals(property)) {
                String protocol = element.getAttribute("protocol");
                if (StringUtils.isNotBlank(protocol)) {
                    bd.getPropertyValues().addPropertyValue("protocol", new RuntimeBeanReference(protocol));
                }

            } else if ("ref".equals(property)) {
                String ref = element.getAttribute("ref");
                if (StringUtils.isNotBlank(ref)) {
                    bd.getPropertyValues().addPropertyValue("ref", new RuntimeBeanReference(ref));
                }
            } else {
                System.out.println("property:" + property);
                bd.getPropertyValues().addPropertyValue(property, new TypedStringValue(value));
            }
        }

        if (ProtocolConfig.class.equals(beanClass)) {
            //解析配置中的 parameter 元素 ,返回 parameters
            ManagedMap parameters = parseParameters(element);
            //将 attributes 也加入到 parameters中
            NamedNodeMap attributes = element.getAttributes();
            int len = attributes.getLength();
            for (int i = 0; i < len; i++) {
                Node node = attributes.item(i);
                String localName = node.getLocalName();
                if (parameters == null) {
                    parameters = new ManagedMap();
                }
                parameters.put(localName, new TypedStringValue(node.getNodeValue(), String.class));
            }
            if (parameters != null) {
                bd.getPropertyValues().addPropertyValue("parameters", parameters);
            }
        }

        parserContext.getRegistry().registerBeanDefinition(id, bd);
        return bd;
    }


    private ManagedMap parseParameters(Element element) {
        NodeList nodeList = element.getChildNodes();
        ManagedMap parameters = null;
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println(node.getNodeName() + ":" + node.getLocalName());
                if ("parameter".equals(node.getLocalName()) || "parameter".equals(node.getLocalName())) {
                    String key = ((Element) node).getAttribute("key");
                    String value = ((Element) node).getAttribute("value");
                    if (parameters == null) {
                        parameters = new ManagedMap();
                    }
                    parameters.put(key, value);
                }
            }
        }
        return parameters;
    }
}
