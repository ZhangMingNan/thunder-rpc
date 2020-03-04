package org.ming.thunder.sample.test;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class EmbeddedZookeeper {
    private ZooKeeperServerMain zookeeperServer;
    private Thread t1;

    public void start() throws IOException, QuorumPeerConfig.ConfigException {
        Properties properties = new Properties();
        InputStream in = EmbeddedZookeeper.class.getResourceAsStream("/zoo.cfg");
        properties.load(in);

        QuorumPeerConfig quorumConfiguration = new QuorumPeerConfig();
        quorumConfiguration.parseProperties(properties);
        in.close();

        zookeeperServer = new ZooKeeperServerMain();
        final ServerConfig configuration = new ServerConfig();
        configuration.readFrom(quorumConfiguration);
        t1 = new Thread(() -> {
            try {
                zookeeperServer.runFromConfig(configuration);
            } catch (IOException e) {
            }
        });
        t1.start();
    }
}