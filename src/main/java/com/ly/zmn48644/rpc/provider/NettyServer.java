package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.model.RpcRequest;
import com.ly.zmn48644.rpc.serializer.SerializerType;
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
 * 作者:张明楠(1007350771@qq.com)
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
    public void start(final int port, final Object serviceObject) {

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
                            ch.pipeline().addLast(new NettyDecoderHandler(RpcRequest.class, SerializerType.JAVA));
                            //注册编码器NettyEncoderHandler
                            ch.pipeline().addLast(new NettyEncoderHandler(SerializerType.JAVA));
                            //注册服务端业务逻辑处理器NettyServerInvokeHandler
                            ch.pipeline().addLast(new NettyServerInvokeHandler(serviceObject));
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














