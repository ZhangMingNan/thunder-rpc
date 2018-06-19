package com.ly.zmn48644.rpc;

import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class ZookeeperMain {

    private static EmbeddedZookeeper zookeeper;

    /**
     * 启动zookeeper
     * @param args
     * @throws IOException
     * @throws QuorumPeerConfig.ConfigException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, QuorumPeerConfig.ConfigException, InterruptedException {
        zookeeper = new EmbeddedZookeeper();
        zookeeper.start();
        System.out.println("zookeeper 启动完成");
        Thread.sleep(1000000);
    }
}
