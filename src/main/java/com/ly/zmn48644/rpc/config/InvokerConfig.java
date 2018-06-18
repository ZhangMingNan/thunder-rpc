package com.ly.zmn48644.rpc.config;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class InvokerConfig {
    private Integer timeout;

    private String appKey;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
