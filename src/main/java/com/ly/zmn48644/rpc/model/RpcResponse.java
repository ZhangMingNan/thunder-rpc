package com.ly.zmn48644.rpc.model;

import java.io.Serializable;
/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class RpcResponse implements Serializable{
    private String requestId;
    private Object result;

    @Override
    public String toString() {
        return "RpcResponse{" +
                "requestId='" + requestId + '\'' +
                ", result=" + result +
                '}';
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
