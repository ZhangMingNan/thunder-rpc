package com.ly.zmn48644.rpc.registry.zookeeper;

import com.ly.zmn48644.rpc.config.closable.ThunderShutdownHook;
import com.ly.zmn48644.rpc.registry.AbstractRegistry;
import com.ly.zmn48644.rpc.rpc.URL;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperRegistry extends AbstractRegistry {
    private static final String ROOT = "/thunder";
    private static final String PROVIDER_TYPE = "provider";

    //zk客户端
    private ZkClient zkClient;

    public ZookeeperRegistry(ZkClient zkClient) {
        this.zkClient = zkClient;
        //创建根节点
        if (!zkClient.exists(ROOT)) {
            zkClient.createPersistent(ROOT);
        }
        //注册用于关闭zkClient!
        ThunderShutdownHook.register(this);
    }


    public void doRegister(URL url) {
        //获取到提供者列表
        String host = url.getHost();
        int port = url.getPort();
        String service = url.getPath();
        String address = host + ":" + port;
        String path = ROOT + "/" + service + "/" + PROVIDER_TYPE;
        if (zkClient.exists(path)) {
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


    public List<URL> doDiscover(URL url) {
        List<String> strings = zkClient.getChildren(ROOT);
        List<URL> serviceUrls = new ArrayList<>();
        for (String path : strings) {
            String addressList = zkClient.readData(ROOT + "/" + path + "/" + PROVIDER_TYPE);
            System.out.println(path);
            String[] addressArray = StringUtils.split(addressList, "|");
            for (String address : addressArray) {
                System.out.println("   " + address);
                String host = StringUtils.substringBefore(address, ":");
                String port = StringUtils.substringAfter(address, ":");
                Map<String, String> map = new HashMap<>();
                //TODO 临时处理
                URL serviceUrl = new URL("", host, Integer.valueOf(port), path, map);
                serviceUrls.add(serviceUrl);
            }
        }
        return serviceUrls;
    }

    @Override
    public void doUnRegister(URL url) {

    }

    @Override
    public void doSubscribe(URL url) {

    }

    @Override
    public void doUnSubscribe(URL url) {

    }

    @Override
    protected void doClose() {
        System.out.println(" 关闭 zookeeper client !");
        zkClient.close();
    }
}
