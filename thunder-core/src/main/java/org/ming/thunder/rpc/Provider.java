package org.ming.thunder.rpc;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface Provider<T> {

    Class<?> getInterface();

    T getImpl();

    //TODO 抽取到 Call 接口中
    Response call(Request request);

    //TODO 抽取到 Node 接口中
    URL getUrl();
}
