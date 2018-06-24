package com.ly.zmn48644.rpc.transport;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;

public abstract class AbstractSharePoolClient implements Client {
    protected ArrayList<Channel> channels;
    private SharedObjectFactory<Channel> sharedObjectFactory;
    //TODO 临时写死初始化 channel 大小
    protected int connections = 5;


    protected void initPool() {
        channels = new ArrayList<>();
        sharedObjectFactory = createChannelFactory();

        for (int i = 0; i < connections; i++) {
            channels.add(sharedObjectFactory.makeObject());
        }

        //TODO 通过配置决定是异步初始化还是同步初始化.
        //这里临时使用同步初始化
        initConnections();
    }

    private void initConnections() {
        for (Channel channel : channels) {
            channel.open();
        }
    }

    protected Channel getChannel() {
        //TODO 这里临时随机选择一个 channel
        return channels.get(RandomUtils.nextInt(0, channels.size() - 1));
    }

    protected abstract SharedObjectFactory createChannelFactory();
}
