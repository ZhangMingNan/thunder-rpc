package com.ly.zmn48644.rpc.revoker;

import com.google.common.collect.Maps;
import com.ly.zmn48644.rpc.model.RpcResponse;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RevokerResponseHolder {
    private static Map<String, RpcResponseWrapper> responseWrapperMap = Maps.newConcurrentMap();


    /**
     * 初始化
     *
     * @param requestId
     */
    public static void initResponseData(String requestId) {
        RpcResponseWrapper wrapper = new RpcResponseWrapper();
        responseWrapperMap.put(requestId, wrapper);
    }

    public static RpcResponse getResponse(String requestId) {
        RpcResponseWrapper wrapper = responseWrapperMap.get(requestId);
        try {
            return wrapper.getBlockingQueue().poll(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void putResultValue(RpcResponse rpcResponse) {
        RpcResponseWrapper wrapper = responseWrapperMap.get(rpcResponse.getRequestId());
        wrapper.getBlockingQueue().add(rpcResponse);
        responseWrapperMap.put(rpcResponse.getRequestId(),wrapper);
    }
}
