package com.ly.zmn48644.thunder.transport.netty;

import com.ly.zmn48644.common.threadpool.ThreadPool;
import com.ly.zmn48644.common.threadpool.cached.CachedThreadPool;
import com.ly.zmn48644.rpc.Request;
import com.ly.zmn48644.rpc.Response;
import com.ly.zmn48644.rpc.URL;

import com.ly.zmn48644.thunder.common.threadpool.ThreadPool;
import com.ly.zmn48644.thunder.rpc.Response;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.transport.AbstractServer;
import com.ly.zmn48644.transport.MessageHandler;
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

import java.util.concurrent.Executor;

public class Netty4Server extends AbstractServer {

    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private URL url;

    private MessageHandler messageHandler;


    public Netty4Server(URL url, MessageHandler messageHandler) {
        this.url = url;
        this.messageHandler = messageHandler;
    }

    @Override
    public boolean open() {
        //防止重复启动
        if (bossGroup != null || workerGroup != null) {
            return false;
        }
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();


        ThreadPool threadPool = new CachedThreadPool(url);
        Executor executor = threadPool.getExecutor();

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
                        ch.pipeline().addLast(new NettyDecoder());
                        //注册编码器NettyEncoderHandler
                        ch.pipeline().addLast(new NettyEncoder());
                        //注册服务端业务逻辑处理器NettyServerInvokeHandler
                        ch.pipeline().addLast(new NettyChannelHandler(messageHandler, executor));
                    }
                });

        try {
            channel = serverBootstrap.bind(url.getPort()).sync().channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
    }

    @Override
    public Response request(Request request) {
        throw new RuntimeException("server 不支持request!");
    }
}
