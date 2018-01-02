package com.ly.zmn48644.rpc.revoker.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RevokerInvocationHandler implements InvocationHandler {

    private Class<?> targetService;

    public RevokerInvocationHandler(Class<?> targetService) {
        this.targetService = targetService;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String targetServiceName = targetService.getName();

        //从配置中心获取到服务提供者IP和端口数据
        System.out.println("代理执行" + targetServiceName + "接口的" + method.getName());


        return "模拟代理返回!";
    }
}
