package com.ly.zmn48644.rpc;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperMain {

    private static EmbeddedZookeeper zookeeper;
    private static ZkClient zkClient;

    public static void main(String[] args) throws IOException, QuorumPeerConfig.ConfigException, InterruptedException {
        Properties properties = new Properties();
        InputStream in = EmbeddedZookeeper.class.getResourceAsStream("/zoo.cfg");
        properties.load(in);
        int port = Integer.parseInt(properties.getProperty("clientPort"));
        in.close();
        zookeeper = new EmbeddedZookeeper();
        zookeeper.start();
        System.out.println("zookeeper 启动完成");
        Thread.sleep(100000);
    }


}
