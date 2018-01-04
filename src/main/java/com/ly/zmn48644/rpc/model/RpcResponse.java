package com.ly.zmn48644.rpc.model;

import java.io.Serializable;

public class RpcResponse implements Serializable{
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
