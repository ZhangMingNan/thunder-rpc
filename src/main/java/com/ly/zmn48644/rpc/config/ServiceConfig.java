package com.ly.zmn48644.rpc.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ly.zmn48644.rpc.common.URLParamType;
import com.ly.zmn48644.rpc.config.handler.ConfigHandler;
import com.ly.zmn48644.rpc.config.handler.SimpleConfigHandler;
import com.ly.zmn48644.rpc.provider.NettyServer;
import com.ly.zmn48644.rpc.registry.Provider;
import com.ly.zmn48644.rpc.registry.ZookeeperRegistry;
import com.ly.zmn48644.rpc.rpc.Exporter;
import com.ly.zmn48644.rpc.rpc.URL;
import com.ly.zmn48644.rpc.utils.NetUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class ServiceConfig<T> extends AbstractInterfaceConfig {



    //服务被调用超时时间
    private int timeout;
    //服务启动端口
    private int serverPort;
    //服务提供者唯一标识
    private String appKey;

    private Class<T> interfaceClass;

    private T ref;

    private List<Exporter<T>> exporters = new CopyOnWriteArrayList<>();

    protected void export() {

        URL registryUrl = loadRegistryUrl();

        doExport(protocol, registryUrl);
    }

    private void doExport(ProtocolConfig protocolConfig, URL registryURL) {
        String protocolName = protocolConfig.getName();
        Integer port = serverPort;
        Map<String, String> map = new HashMap<>();
        collectConfigParams(map,protocolConfig,registry,this);


        String hostAddress = null;
        if (NetUtils.isInvalidLocalHost(hostAddress)) {
            hostAddress = getLocalHostAddress(Lists.newArrayList(registryURL));
        }

        URL serviceUrl = new URL(protocolName, hostAddress, port, interfaceClass.getName(), map);
        registryURL.addParameter(URLParamType.embed.name(),serviceUrl.toFullStr());
        ConfigHandler configHandler = new SimpleConfigHandler();
        exporters.add(configHandler.export(interfaceClass, ref, registryURL));

    }



    private URL loadRegistryUrl() {
        String protocol = this.registry.getRegProtocol();
        String host = this.registry.getHost();
        int port = this.registry.getPort();
        String path = RegistryConfig.class.getName();
        Map<String,String> map = Maps.newHashMap();
        URL registryUrl = new URL(protocol, host, port, path,map);
        return registryUrl;
    }


    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {

        this.ref = ref;
    }

    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
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
