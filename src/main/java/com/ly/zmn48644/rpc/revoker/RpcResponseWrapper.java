package com.ly.zmn48644.rpc.revoker;

import com.ly.zmn48644.rpc.model.RpcResponse;

import java.util.concurrent.ArrayBlockingQueue;

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
