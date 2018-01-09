package com.ly.zmn48644.rpc.registry;

/**
 * 封装服务提供者数据
 */
public class Provider {
    //提供的服务名称
    private String service;
    //提供者地址
    private String host;
    //提供者端口
    private int port;

    public Provider(String service, String host, int port) {
        this.service = service;
        this.host = host;
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
