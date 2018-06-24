package com.ly.zmn48644.rpc.rpc;

/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public interface ResponseFuture extends Response {
    void onSuccess(Response response);

    void onFailure(Response response);
}
