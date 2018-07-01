package com.ly.zmn48644.thunder.rpc;

/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public class DefaultResponse implements Response {

    private long requestId;

    private Object value;

    public DefaultResponse() {
    }

    public DefaultResponse(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "requestId=" + requestId +
                ", value=" + value +
                '}';
    }
}
