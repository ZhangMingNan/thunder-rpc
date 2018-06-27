package com.ly.zmn48644.rpc.registry.zookeeper;

import com.ly.zmn48644.rpc.registry.Registry;
import com.ly.zmn48644.rpc.registry.RegistryFactory;
import com.ly.zmn48644.rpc.rpc.URL;
import org.I0Itec.zkclient.ZkClient;


/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperRegistryFactory implements RegistryFactory {

    @Override
    public Registry getRegistry(URL url) {
        ZkClient zkClient = new ZkClient(url.getHost() + ":" + url.getPort(), 5000);
        return new ZookeeperRegistry(zkClient);
    }
}
