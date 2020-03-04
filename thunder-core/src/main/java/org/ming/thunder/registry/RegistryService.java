package org.ming.thunder.registry;

import org.ming.thunder.rpc.URL;

/**
 * 作者：张明楠
 * 时间：2018/6/29
 */
public interface RegistryService {

    /**
     * 注册服务
     *
     * @param url
     */
    void register(URL url);

    /**
     * 注销服务
     *
     * @param url
     */
    void unRegister(URL url);
}
