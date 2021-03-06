package org.ming.thunder.transport.netty4;

import org.ming.thunder.common.threadpool.ThreadPool;
import org.ming.thunder.common.threadpool.cached.CachedThreadPool;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;
import org.ming.thunder.rpc.ResponseFuture;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.AbstractSharePoolClient;
import org.ming.thunder.transport.Channel;
import org.ming.thunder.transport.MessageHandler;
import org.ming.thunder.transport.SharedObjectFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;

/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public class Netty4Client extends AbstractSharePoolClient {

    private URL url;
    private Bootstrap bootstrap;
    protected ConcurrentMap<Long, ResponseFuture> callbackMap = new ConcurrentHashMap<>();

    public Netty4Client(URL url) {
        this.url = url;
    }

    @Override
    public boolean open() {

        ThreadPool threadPool = new CachedThreadPool(url);
        Executor executor = threadPool.getExecutor();
        //创建客户端线程组
        EventLoopGroup group = new NioEventLoopGroup(10);
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //注册Netty编码器
                        ch.pipeline().addLast(new NettyEncoder());
                        //注册Netty解码器
                        ch.pipeline().addLast(new NettyDecoder());
                        //注册客户端业务逻辑处理handler`
                        ch.pipeline().addLast(new NettyChannelHandler(new MessageHandler() {
                            @Override
                            public Object handle(Object message) {
                                Response response = Response.class.cast(message);
                                ResponseFuture responseFuture = removeCallback(response.getRequestId());
                                responseFuture.onSuccess(response);
                                return null;
                            }
                        }, executor));
                    }
                });

        //初始化 channel pool
        initPool();

        return true;
    }


    public void registerCallback(Long requestId, ResponseFuture responseFuture) {
        this.callbackMap.put(requestId, responseFuture);
    }

    public ResponseFuture removeCallback(Long requestId) {
        return this.callbackMap.remove(requestId);
    }


    @Override
    public void close() {

    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public Response request(Request request) {
        Channel channel = getChannel();
        Response response = channel.request(request);
        return response;
    }


    @Override
    protected SharedObjectFactory createChannelFactory() {
        return new NettySharedObjectFactory(this);
    }
}
