package com.ly.zmn48644.rpc.revoker.proxy;

import com.ly.zmn48644.rpc.model.RpcRequest;
import com.ly.zmn48644.rpc.model.RpcResponse;
import com.ly.zmn48644.rpc.provider.NettyDecoderHandler;
import com.ly.zmn48644.rpc.provider.NettyEncoderHandler;
import com.ly.zmn48644.rpc.revoker.NettyClientInvokeHandler;
import com.ly.zmn48644.rpc.serializer.SerializerType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RevokerInvocationHandler implements InvocationHandler {

    private Class<?> targetService;

    private InetSocketAddress address;

    public RevokerInvocationHandler(Class<?> targetService, InetSocketAddress address) {
        this.targetService = targetService;
        this.address = address;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return null;
        }

        String targetServiceName = targetService.getName();

        //从配置中心获取到服务提供者IP和端口数据
        System.out.println("代理执行" + targetServiceName + "接口的" + method.getName());

        //每次调用都创建一个 channel 发送请求并获取到返回结果

        final ArrayBlockingQueue<RpcResponse> queue = new ArrayBlockingQueue<RpcResponse>(1);

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
                        //注册客户端业务逻辑处理handler
                        ch.pipeline().addLast(new NettyClientInvokeHandler(queue));
                    }
                });

        ChannelFuture channelFuture = bootstrap.connect().sync();
        final Channel newChannel = channelFuture.channel();
        //newChannel.writeAndFlush();
        //如果Channel建立成功,返回新建的Channel


        //这里面临的问题就是 怎么获取到返回的数据.
        //这里的调用时同步的调用, netty 返回的数据 是在 invokeHandler中 异步返回的.

        //为每次请求,也就是 当前这个 invoke 方法创建一个容量为1的阻塞队列
        //将这个

        RpcRequest request  = new RpcRequest();
        request.setMethod(method.getName());
        request.setService(targetServiceName);
        request.setObjects(args);

        ChannelFuture future =  newChannel.writeAndFlush(request);


        future.syncUninterruptibly();

        RpcResponse rpcResponse = queue.poll(100, TimeUnit.SECONDS);

        return rpcResponse.getResult();
    }


}























