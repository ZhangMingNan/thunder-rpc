package com.ly.zmn48644.rpc.rpc;


import java.lang.reflect.Method;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultProvider<T> implements Provider<T> {

    protected Class<T> clz;
    protected URL url;
    protected T proxyImpl;

    public DefaultProvider(Class<T> clz, T proxyImpl, URL url) {
        this.clz = clz;
        this.url = url;
        this.proxyImpl = proxyImpl;
    }

    @Override
    public Class<T> getInterface() {
        return clz;
    }

    @Override
    public T getImpl() {
        return proxyImpl;
    }

    @Override
    public Response call(Request request) {
        DefaultResponse response = new DefaultResponse();
        try {
            Method method = clz.getMethod(request.getMethodName(),String.class);
            Object result = method.invoke(proxyImpl, request.getArguments());
            response.setValue(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public URL getUrl() {
        return url;
    }
}
