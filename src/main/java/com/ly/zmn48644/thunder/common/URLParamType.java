package com.ly.zmn48644.thunder.common;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public enum URLParamType {

    embed("embed", "");
    private String name;
    private String value;
    URLParamType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
