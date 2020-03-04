package org.ming.thunder.registry;

import org.ming.thunder.rpc.URL;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/29
 */
public interface DiscoveryService {
    /**
     * 拉取服务接口
     *
     * @param url
     * @return
     */
    List<URL> discover(URL url);

    /**
     * 订阅服务
     *
     * @param url
     */
    void subscribe(URL url);

    /**
     * 取消订阅服务
     *
     * @param url
     */
    void unSubscribe(URL url);
}
