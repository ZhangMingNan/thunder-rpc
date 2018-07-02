package com.ly.zmn48644.thunder.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class RpcRequest implements Serializable {

    private String service;

    private String method;

    private Object[] objects;

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "service='" + service + '\'' +
                ", method='" + method + '\'' +
                ", objects=" + Arrays.toString(objects) +
                '}';
    }
}
