package com.ly.zmn48644.rpc.rpc;


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


        return null;
    }
}
