package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperRegistry {
    private static final String ROOT = "/thunder";
    private static final String PROVIDER_TYPE = "provider";
    private static final String INVOKER_TYPE = "invoker";

    //zk客户端
    private ZkClient zkClient;

    public ZookeeperRegistry(ZkClient zkClient) {
        this.zkClient = zkClient;
        //创建根节点
        zkClient.createPersistent(ROOT);
    }

    /**
     * 向注册中心注册调用者信息
     */
    public void registerInvoker() {

    }

    /**
     * 向注册中心注册服务提供者信息
     */
    public void registerProvider(Provider provider) {
        //获取到提供者列表
        String host = provider.getHost();
        int port = provider.getPort();
        String service = provider.getService();
        String address = host + ":" + port;
        String path = ROOT + "/" + service + "/" + PROVIDER_TYPE;
        if (!zkClient.exists(path)) {
            zkClient.writeData(path, address);
        } else {
            zkClient.createPersistent(path, address);
        }
    }

    /**
     * 从注册中心拉取所有的服务提供者信息
     */
    public List<Provider> pullProvider() {

        return null;
    }
}
