package org.ming.thunder.config;


import org.ming.thunder.config.closable.ThunderShutdownHook;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
public class AbstractConfig {

    static{
        Runtime.getRuntime().addShutdownHook(ThunderShutdownHook.getShutdownHook());
    }

    /**
     * 汇总配置参数
     *
     * @param map
     */
    protected void collectConfigParams(Map<String, String> map, AbstractConfig... configs) {
        //通过反射的方式获取到 所有继承 AbstractConfig 类的 属性, 将属性名和值放入map 中去.
        for (AbstractConfig config : configs) {
            if (config != null) {
                config.appendConfigParams(map);
            }
        }
    }

    private void appendConfigParams(Map<String, String> parameters) {
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            try {
                String name = method.getName();
                //判断是否是配置属性的get方法
                if (isConfigMethod(method)) {
                    int idx = name.startsWith("get") ? 3 : 2;
                    //获取属性名称
                    String key = name.substring(idx, idx + 1).toLowerCase() + name.substring(idx + 1);
                    //调用此方法获取配置项的值
                    Object value = method.invoke(this);
                    //将配置添加到传入的 map 中去.
                    parameters.put(key, String.valueOf(value).trim());
                } else if ("getParameters".equals(name) && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0 && method.getReturnType() == Map.class) {
                    Map<String, String> map = (Map<String, String>) method.invoke(this);
                    parameters.putAll(map);
                }
            } catch (Exception e) {
                throw new RuntimeException(String.format("appendConfigParams error!"));
            }
        }
    }

    private boolean isConfigMethod(Method method) {
        boolean checkMethod =
                (method.getName().startsWith("get") || method.getName().startsWith("is")) && !"isDefault".equals(method.getName())
                        && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0
                        && isPrimitive(method.getReturnType());
        return checkMethod;
    }

    private boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() || type == String.class || type == Character.class || type == Boolean.class || type == Byte.class
                || type == Short.class || type == Integer.class || type == Long.class || type == Float.class || type == Double.class;
    }
}
