package com.ly.zmn48644.rpc.transport;

public interface Channel {
    //开启通道
    boolean open();

    //关闭通道
    void close();

    //发送请求
    Response request(Request request);
}
