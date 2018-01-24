package com.ly.zmn48644.rpc.invoker.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class InvokerProxyBeanFactory {
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
