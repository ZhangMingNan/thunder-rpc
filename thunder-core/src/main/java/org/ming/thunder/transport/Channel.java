package org.ming.thunder.transport;


import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;

public interface Channel {
    //开启通道
    boolean open();

    //关闭通道
    void close();

    //发送请求
    Response request(Request request);
}
