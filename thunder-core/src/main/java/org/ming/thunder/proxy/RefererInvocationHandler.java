package org.ming.thunder.proxy;


import org.ming.thunder.cluster.Cluster;
import org.ming.thunder.rpc.DefaultRequest;
import org.ming.thunder.rpc.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public class RefererInvocationHandler<T> implements InvocationHandler {

    private Cluster cluster;

    private Class<T> interfaceClass;

    public RefererInvocationHandler(Class<T> interfaceClass, Cluster cluster) {
        this.cluster = cluster;
        this.interfaceClass = interfaceClass;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if ("toString".equals(method.getName())) {
            return null;
        }
        if ("equals".equals(method.getName())) {
            return null;
        }

        DefaultRequest request = new DefaultRequest();
        request.setRequestId(System.currentTimeMillis());
        request.setMethodName(method.getName());
        request.setArguments(args);
        request.setInterfaceName(interfaceClass.getName());
        Response call = cluster.call(request);
        return call.getValue();
    }
}
