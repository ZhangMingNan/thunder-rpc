package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class ZookeeperRegistryFactory {
    public ZookeeperRegistry build(String host, int port) {
        ZkClient zkClient = new ZkClient(host + ":" + port, 5000);
        return new ZookeeperRegistry(zkClient);
    }
}
