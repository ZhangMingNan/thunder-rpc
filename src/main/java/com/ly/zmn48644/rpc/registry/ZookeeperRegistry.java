package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 注册中心
 */
public class ZookeeperRegistry {

    //zk客户端
    private ZkClient zkClient;

    public ZookeeperRegistry(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * 向注册中心注册调用者信息
     */
    public void registerInvoker() {

    }

    /**
     * 向注册中心注册服务提供者信息
     */
    public void registerProvider(List<Provider> providerList) {

    }

    /**
     * 从注册中心拉取所有的服务提供者信息
     */
    public List<Provider> pullProvider() {

        return null;
    }
}
