package com.ly.zmn48644.rpc.config;

public class ProtocolConfig {
    //服务协议名
    String name;
    //序列化方式
    String serialization;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }
}
