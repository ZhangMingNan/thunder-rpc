package com.ly.zmn48644.rpc.config;

import java.util.Map;

public class ProtocolConfig extends AbstractConfig {
    //服务协议名
    String name;
    //序列化方式
    String serialization;


    // 具体实现的扩展参数.
    private Map<String, String> parameters;


    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

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
