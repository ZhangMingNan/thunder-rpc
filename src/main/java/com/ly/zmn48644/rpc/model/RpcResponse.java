package com.ly.zmn48644.rpc.model;

public class RpcResponse {
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "result=" + result +
                '}';
    }
}
