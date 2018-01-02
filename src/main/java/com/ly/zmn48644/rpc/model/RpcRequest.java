package com.ly.zmn48644.rpc.model;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private String method;

    private Object[] objects;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
