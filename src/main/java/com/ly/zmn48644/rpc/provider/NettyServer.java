package com.ly.zmn48644.rpc.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 底层通讯,这里使用单列模式.
 * <p>
 * 网络IO基础知识
 * 通讯的本质是IO.
 * Linux操作系统下的5种网络IO模型
 * Java语言实现的IO模型
 * jdk1.4之前的版本 提供同步阻塞模型 - > 性能上无法和c/c++相比
 * jdk1.4 中引入了NIO 支持了IO多路复用的编程模型 , 于是产生了一批底层通讯框架比如 mina netty grizzly等.
 * jdk1.7 中升级了NIO 并且引入了 异步IO(NIO2)
 * <p>
 * IO基础知识
 * <p>
 * Socket基础知识
 * 在网络上传输的是一个一个的数据报,单个数据报的大小是有限制的,因此数据经常会被拆分成多个数据报进行传输,在传输过程中
 * 会出现丢包或者损坏的情况,这是就要重新传送,还会出现 收到的数据报顺序是混乱的,所以需要重新排序.
 * Socket 对开发人员封装了上面的这些具体细节.
 */
public class NettyServer {

    private Channel channel;

    private NettyServer() {
        //构造方法私有
    }

    private static final NettyServer nettyServer = new NettyServer();


    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    /**
     * 指定端口启动netty
     * @param port
     */
    public void start(final int port) {
        synchronized (NettyServer.class) {
            //防止重复启动
            if (bossGroup != null || workerGroup != null) {
                return;
            }
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //注册解码器NettyDecoderHandler
                            //ch.pipeline().addLast(new NettyDecoderHandler(AresRequest.class, serializeType));
                            //注册编码器NettyEncoderHandler
                            //ch.pipeline().addLast(new NettyEncoderHandler(serializeType));
                            //注册服务端业务逻辑处理器NettyServerInvokeHandler
                            ch.pipeline().addLast(new NettyServerInvokeHandler());
                        }
                    });

            try {
                channel = serverBootstrap.bind(port).sync().channel();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭netty
     */
    public void stop() {
        if (null == channel) {
            throw new RuntimeException("Netty Server Stoped");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
    }
    public static NettyServer server(){
        return nettyServer;
    }
}














