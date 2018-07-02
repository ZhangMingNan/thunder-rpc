package com.ly.zmn48644.thunder.config;

import com.google.common.collect.Lists;


import com.ly.zmn48644.thunder.common.URLParamType;
import com.ly.zmn48644.thunder.config.handler.ConfigHandler;
import com.ly.zmn48644.thunder.config.handler.SimpleConfigHandler;
import com.ly.zmn48644.thunder.rpc.Exporter;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.thunder.utils.NetUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class ServiceConfig<T> extends AbstractInterfaceConfig {


    //服务被调用超时时间
    protected int timeout;
    //服务启动端口
    protected int serverPort;
    //服务提供者唯一标识
    protected String appKey;

    protected Class<T> interfaceClass;

    protected T ref;

    protected List<Exporter<T>> exporters = new CopyOnWriteArrayList<>();

    protected void export() {

        URL registryUrl = loadRegistryUrl();

        doExport(protocol, registryUrl);
    }

    private void doExport(ProtocolConfig protocolConfig, URL registryURL) {
        String protocolName = protocolConfig.getName();
        Integer port = serverPort;
        Map<String, String> map = new HashMap<>();

        collectConfigParams(map, protocolConfig, registry, this);


        String hostAddress = null;
        if (NetUtils.isInvalidLocalHost(hostAddress)) {
            hostAddress = getLocalHostAddress(Lists.newArrayList(registryURL));
        }

        URL serviceUrl = new URL(protocolName, hostAddress, port, interfaceClass.getName(), map);
        registryURL.addParameter(URLParamType.embed.name(), serviceUrl.toFullStr());
        ConfigHandler configHandler = new SimpleConfigHandler();

        Exporter<T> export = configHandler.export(interfaceClass, ref, registryURL);
        exporters.add(export);
    }


    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {

        this.ref = ref;
    }

    public void setInterface(Class<T> interfaceClass) {

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
