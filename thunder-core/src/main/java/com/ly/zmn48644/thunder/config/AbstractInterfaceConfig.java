package com.ly.zmn48644.thunder.config;

import com.google.common.collect.Maps;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.thunder.utils.NetUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractInterfaceConfig extends AbstractConfig {
    // 对应的注册中心的配置
    protected RegistryConfig registry;
    protected ProtocolConfig protocol;

    public void setRegistry(RegistryConfig registryConfig) {
        this.registry = registryConfig;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    protected String getLocalHostAddress(List<URL> registryURLs) {
        String localAddress = null;
        Map<String, Integer> regHostPorts = new HashMap<String, Integer>();
        for (URL ru : registryURLs) {
            if (StringUtils.isNotBlank(ru.getHost()) && ru.getPort() > 0) {
                regHostPorts.put(ru.getHost(), ru.getPort());
            }
        }
        InetAddress address = NetUtils.getLocalAddress(regHostPorts);
        if (address != null) {
            localAddress = address.getHostAddress();
        }

        if (NetUtils.isValidLocalHost(localAddress)) {
            return localAddress;
        }
        throw new RuntimeException("Please config local server hostname with intranet IP first!");
    }


    protected URL loadRegistryUrl() {
        String protocol = this.registry.getRegProtocol();
        String host = this.registry.getHost();
        int port = this.registry.getPort();
        String path = RegistryConfig.class.getName();
        Map<String, String> map = Maps.newHashMap();
        URL registryUrl = new URL(protocol, host, port, path, map);
        return registryUrl;
    }
}
