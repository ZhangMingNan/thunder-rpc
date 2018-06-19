package com.ly.zmn48644.rpc.rpc;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface Protocol {

    /**
     * 暴露服务
     *
     * @param provider
     * @param url
     * @param <T>
     * @return
     */
    <T> Exporter<T> export(Provider<T> provider, URL url);


//    <T> Referer<T> refer(Class<T> clz, URL url, URL serviceUrl);


    /**
     * 销毁
     */
    void destroy();
}
