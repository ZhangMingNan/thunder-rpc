package com.ly.zmn48644.rpc.revoker.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RevokerProxyBeanFactory {
    /**
     * 创建代理实例
     *
     * @param clz
     * @param invocationHandler
     * @return
     */
    public Object getProxy(Class<?> clz, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clz}, invocationHandler);
    }
}
