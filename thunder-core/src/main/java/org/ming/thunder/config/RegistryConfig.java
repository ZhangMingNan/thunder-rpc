package org.ming.thunder.config;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class RegistryConfig extends AbstractConfig{
    // 注册配置名称
    private String name;
    // 注册协议
    private String regProtocol;
    // 注册中心地址
    private String host;
    // 注册中心端口号
    private int port;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegProtocol() {
        return regProtocol;
    }

    public void setRegProtocol(String regProtocol) {
        this.regProtocol = regProtocol;
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

    @Override
    public String toString() {
        return "RegistryConfig{" +
                "name='" + name + '\'' +
                ", regProtocol='" + regProtocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
