package com.ly.zmn48644.rpc.invoker;

import com.ly.zmn48644.rpc.model.RpcRequest;
import com.ly.zmn48644.rpc.model.RpcResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class InvokerServiceCallable implements Callable<RpcResponse> {
    private RpcRequest request;
    private InetSocketAddress socketAddress;

    public InvokerServiceCallable(RpcRequest request, InetSocketAddress channel) {
        this.request = request;
        this.socketAddress = channel;
    }

    public RpcResponse call() throws Exception {
        ArrayBlockingQueue<Channel> blockingQueue = NettyChannelPoolFactory.instance().acquire(socketAddress);
        //如果Channel建立成功,返回新建的Channel
        Channel channel = blockingQueue.poll(100, TimeUnit.SECONDS);
        try {
            //为每次请求,也就是 当前这个 invoke 方法创建一个容量为1的空阻塞队列
            //invoke 从此空阻塞队列获取结果,并在此方法等待. 于此同时 channelRead0 方法如果有返回结果响应则将返回
            //结果放入到空的阻塞队列中去,此时 invoke 方法结束阻塞 返回响应结果.
            //关键就是利用阻塞队列来时先同步等待返回.
            ChannelFuture future = channel.writeAndFlush(request);
            future.syncUninterruptibly();
            //这里注意 poll 方法无参数是非阻塞的,只有设置了超时时间就会变成阻塞的.
            return RpcResponse.class.cast(InvokerResponseHolder.getResponse(request.getRequestId()));
        } catch (Exception e) {
            throw new RuntimeException("发送网络请求异常!");
        } finally {
            //TODO 将使用后的channel释放
            NettyChannelPoolFactory.instance().release(socketAddress,channel);
        }
    }
}
