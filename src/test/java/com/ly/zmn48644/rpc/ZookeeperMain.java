package com.ly.zmn48644.rpc;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

public class ZookeeperMain {

    private static EmbeddedZookeeper zookeeper;
    private static ZkClient zkClient;

    @BeforeClass
    public static void setUp() throws Exception {
        Properties properties = new Properties();
        InputStream in = EmbeddedZookeeper.class.getResourceAsStream("/zoo.cfg");
        properties.load(in);
        int port = Integer.parseInt(properties.getProperty("clientPort"));
        in.close();
        zookeeper = new EmbeddedZookeeper();
        zookeeper.start();
        Thread.sleep(1000);
        zkClient = new ZkClient("127.0.0.1:" + port, 5000);
        System.out.println("zookeeper 启动完成");
    }


    @Test
    public void testZkClient() {
        List<String> children = zkClient.getChildren("/");

    }

}
