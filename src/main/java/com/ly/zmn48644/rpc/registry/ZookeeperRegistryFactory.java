package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperRegistryFactory {
    public ZookeeperRegistry build(String host, int port) {
        ZkClient zkClient = new ZkClient(host + ":" + port, 5000);
        return new ZookeeperRegistry(zkClient);
    }
}
