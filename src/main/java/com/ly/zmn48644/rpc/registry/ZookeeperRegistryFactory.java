package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;

public class ZookeeperRegistryFactory {
    public static ZookeeperRegistry build(String host, int port) {
        ZkClient zkClient = new ZkClient(host + ":" + port);
        return new ZookeeperRegistry(zkClient);
    }
}
