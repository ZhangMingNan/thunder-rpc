package com.ly.zmn48644.rpc.revoker;

import com.ly.zmn48644.rpc.model.RpcResponse;
import com.ly.zmn48644.rpc.provider.NettyDecoderHandler;
import com.ly.zmn48644.rpc.provider.NettyEncoderHandler;
import com.ly.zmn48644.rpc.serializer.SerializerType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 缓存channel避免每次服务调用都创建新的channel
 */
public class NettyChannelPoolFactory {


    private Map<SocketAddress, ArrayBlockingQueue<Channel>> addressChannelQueueMap;

    private NettyChannelPoolFactory() {

    }

    private static final NettyChannelPoolFactory channelPoolFactory = new NettyChannelPoolFactory();

    public static NettyChannelPoolFactory instance() {
        return channelPoolFactory;
    }

    /**
     * 获取channel队列
     *
     * @param address
     * @return
     */
    public ArrayBlockingQueue<Channel> acquire(InetSocketAddress address) {
        return this.addressChannelQueueMap.get(address);
    }

    /**
     * 初始化
     *
     * @param addressList
     */
    public void initChannelPoolFactory(List<InetSocketAddress> addressList) {
        this.addressChannelQueueMap = new HashMap<SocketAddress, ArrayBlockingQueue<Channel>>();

        try {


            for (SocketAddress socketAddress : addressList) {

                Channel channel = buildChannel(socketAddress);
                ArrayBlockingQueue<Channel> channels = addressChannelQueueMap.get(socketAddress);
                if (channels == null) {
                    channels = new ArrayBlockingQueue<Channel>(1);
                    addressChannelQueueMap.put(socketAddress, channels);
                }
                //添加成功返回 true 否则返回false!
                channels.offer(channel);
            }


        } catch (Exception e) {
            throw new RuntimeException("初始化channel异常!");
        }
    }

    /**
     * 根据 socketAddress 创建
     *
     * @param address
     * @return
     */
    private Channel buildChannel(SocketAddress address) throws InterruptedException {
        //创建客户端线程组
        EventLoopGroup group = new NioEventLoopGroup(10);
        Bootstrap bootstrap = new Bootstrap();
        //设置服务端地址
        bootstrap.remoteAddress(address);

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //注册Netty编码器
                        ch.pipeline().addLast(new NettyEncoderHandler(SerializerType.JAVA));
                        //注册Netty解码器
                        ch.pipeline().addLast(new NettyDecoderHandler(RpcResponse.class, SerializerType.JAVA));
                        //注册客户端业务逻辑处理handler`
                        ch.pipeline().addLast(new NettyClientInvokeHandler());
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect().sync();
        final Channel newChannel = channelFuture.channel();

        return newChannel;
    }

    /**
     * 将使用过的channel放回队列
     *
     * @param address
     * @param channel
     */
    public void release(SocketAddress address, Channel channel) {
        addressChannelQueueMap.get(address).offer(channel);
    }
}
