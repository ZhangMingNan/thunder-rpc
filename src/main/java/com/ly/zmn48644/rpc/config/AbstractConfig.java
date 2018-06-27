package com.ly.zmn48644.rpc.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
public class AbstractConfig {


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
                if (isConfigMethod(method)) {
                    int idx = name.startsWith("get") ? 3 : 2;
                    String key = name.substring(idx, idx + 1).toLowerCase() + name.substring(idx + 1);
                    Object value = method.invoke(this);
                    parameters.put(key, String.valueOf(value).trim());
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
