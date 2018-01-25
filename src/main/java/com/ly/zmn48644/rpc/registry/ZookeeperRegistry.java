package com.ly.zmn48644.rpc.registry;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!zkClient.exists(ROOT)) {
            zkClient.createPersistent(ROOT);
        }
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
        if (zkClient.exists(path)) {

            // zkClient.writeData(path, address);
            String data = zkClient.readData(path, true);
            if (StringUtils.isEmpty(data)) {
                data = StringUtils.EMPTY;
            }
            zkClient.writeData(path, data + "|" + address);
        } else {
            zkClient.createPersistent(path, true);
            zkClient.writeData(path, address);
        }
    }

    /**
     * 从注册中心拉取所有的服务提供者信息
     */
    public Map<String, List<Provider>> pullProvider() {
        List<String> strings = zkClient.getChildren(ROOT);
        Map<String, List<Provider>> map = new HashMap<>();
        for (String string : strings) {
            System.out.println(string);
            String addressList = zkClient.readData(ROOT + "/" + string + "/" + PROVIDER_TYPE);
            String[] addressArray = StringUtils.split(addressList, "|");
            List<Provider> providers = map.get(string);
            for (String address : addressArray) {
                Provider po = new Provider(string, StringUtils.substringBefore(address, ":"), Integer.valueOf(StringUtils.substringAfter(address, ":")));
                if (providers == null) {
                    providers = new ArrayList<>();
                    providers.add(po);
                    map.put(string, providers);
                } else {
                    providers.add(po);
                }
            }
        }
        return map;
    }
}
