package com.ly.zmn48644.rpc.proxy;

import com.ly.zmn48644.rpc.rpc.DefaultRequest;
import com.ly.zmn48644.rpc.rpc.Referer;
import com.ly.zmn48644.rpc.rpc.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public class RefererInvocationHandler<T> implements InvocationHandler {

    private Referer referer;

    private Class<T> interfaceClass;

    public RefererInvocationHandler(Class<T> interfaceClass,Referer referer) {
        this.referer = referer;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DefaultRequest request = new DefaultRequest();
        request.setRequestId(System.currentTimeMillis());
        request.setMethodName(method.getName());
        request.setArguments(args);
        request.setInterfaceName(interfaceClass.getName());
        Response call = referer.call(request);
        return call.getValue();
    }
}
