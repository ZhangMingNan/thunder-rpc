package com.ly.zmn48644.rpc.invoker;

import com.ly.zmn48644.rpc.model.RpcResponse;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class RpcResponseWrapper {

    private ArrayBlockingQueue<RpcResponse> blockingQueue = new ArrayBlockingQueue<RpcResponse>(1);

    public static RpcResponseWrapper of() {
        return new RpcResponseWrapper();
    }

    public ArrayBlockingQueue<RpcResponse> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(ArrayBlockingQueue<RpcResponse> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
}
