package org.ming.thunder.registry.zookeeper;



import org.ming.thunder.extension.SpiMeta;
import org.ming.thunder.registry.Registry;
import org.ming.thunder.registry.RegistryFactory;
import org.ming.thunder.rpc.URL;
import org.I0Itec.zkclient.ZkClient;




/**
 * 作者:张明楠(1007350771@qq.com)
 */
@SpiMeta(name = "zookeeper")
public class ZookeeperRegistryFactory implements RegistryFactory {

    @Override
    public Registry getRegistry(URL url) {
        ZkClient zkClient = new ZkClient(url.getHost() + ":" + url.getPort(), 2000);
        return new ZookeeperRegistry(zkClient);
    }
}
