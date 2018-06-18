package com.ly.zmn48644.rpc.config;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class ProviderConfig<T> {
    //服务被调用超时时间
    private int timeout;
    //服务启动端口
    private int serverPort;
    //服务提供者唯一标识
    private String appKey;

    private T ref;

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
