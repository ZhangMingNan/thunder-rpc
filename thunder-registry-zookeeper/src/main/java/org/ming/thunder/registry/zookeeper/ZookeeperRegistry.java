package org.ming.thunder.registry.zookeeper;

import org.ming.thunder.config.closable.ThunderShutdownHook;

import org.ming.thunder.registry.AbstractRegistry;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.utils.StringTools;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperRegistry extends AbstractRegistry {
    private static final String ROOT = "/thunder";
    private static final String PROVIDERS_TYPE = "provider";
    private static final String REFERER_TYPE = "referer";
    //zk客户端
    private ZkClient zkClient;


    public ZookeeperRegistry(ZkClient zkClient) {
        this.zkClient = zkClient;
        //创建连接状态监听器
        IZkStateListener listener = new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                System.out.println("handleStateChanged:" + state.toString());
                if (state.equals(Watcher.Event.KeeperState.Disconnected)) {
                    //已失去连接
                } else if (state.equals(Watcher.Event.KeeperState.Expired)) {
                    //已经过期
                } else if (state.equals(Watcher.Event.KeeperState.SyncConnected)) {
                    //已连接
                }
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println(" 连接过期后, 重新创建新session ");

            }
        };
        //注册连接状态监听器
        zkClient.subscribeStateChanges(listener);
        //创建根节点
        if (!zkClient.exists(ROOT)) {
            System.out.println("根节点不存在,创建根节点.");
            zkClient.createPersistent(ROOT);
        }
        //注册用于关闭zkClient!
        ThunderShutdownHook.register(this);
    }


    public void doRegister(URL url) {
        String service = url.getPath();
        //TODO 需要优化
        String path = ROOT + "/" + service + "/" + PROVIDERS_TYPE;
        if (!zkClient.exists(path)) {
            zkClient.createPersistent(path, true);
        }
        zkClient.createEphemeral(path + "/" + StringTools.urlEncode(url.toFullStr()));
    }


    public List<URL> doDiscover(URL url) {

        List<URL> serviceUrls = new ArrayList<>();
        List<String> strings = zkClient.getChildren(ROOT);
        for (String path : strings) {
            List<String> providerFullStrList = zkClient.getChildren(ROOT + "/" + path + "/" + PROVIDERS_TYPE);
            for (String fullStr : providerFullStrList) {
                URL providerUrl = URL.valueOf(StringTools.urlDecode(fullStr));
                serviceUrls.add(providerUrl);
            }

        }
        return serviceUrls;
    }

    @Override
    public void doUnRegister(URL url) {
        String service = url.getPath();
        //TODO 需要优化
        String path = ROOT + "/" + service + "/" + PROVIDERS_TYPE + "/" + StringTools.urlEncode(url.toFullStr());
        zkClient.delete(path);
    }

    @Override
    public void doSubscribe(URL url) {
        //创建服务调用者节点
        String service = url.getPath();

        //TODO 需要优化
        String path = ROOT + "/" + service + "/";
        zkClient.createPersistent(path + REFERER_TYPE, true);
        zkClient.createEphemeral(path + REFERER_TYPE + "/" + StringTools.urlEncode(url.toFullStr()));
        //订阅服务提供者节点

        IZkChildListener iZkChildListener = new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) {
                //接收到了服务提供者节点变更的通知

            }
        };
        zkClient.subscribeChildChanges(path + PROVIDERS_TYPE, iZkChildListener);

    }

    @Override
    public void doUnSubscribe(URL url) {
        String service = url.getPath();
        //TODO 需要优化
        String path = ROOT + "/" + service + "/" + REFERER_TYPE + "/" + StringTools.urlEncode(url.toFullStr());
        zkClient.delete(path);

    }

    @Override
    protected void doClose() {
        System.out.println(" 关闭 zookeeper client !");
        zkClient.close();
    }
}
