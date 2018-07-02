package com.ly.zmn48644.thunder.config;

import com.google.common.collect.Lists;

import com.ly.zmn48644.thunder.cluster.Cluster;
import com.ly.zmn48644.thunder.cluster.DefaultCluster;
import com.ly.zmn48644.thunder.protocol.AbstractNode;
import com.ly.zmn48644.thunder.proxy.RefererInvocationHandler;
import com.ly.zmn48644.thunder.registry.Registry;
import com.ly.zmn48644.thunder.registry.RegistryFactory;
import com.ly.zmn48644.thunder.registry.zookeeper.ZookeeperRegistryFactory;
import com.ly.zmn48644.thunder.rpc.DefaultRpcReferer;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.URL;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public class RefererConfig<T> extends AbstractInterfaceConfig {


    protected String serviceInterface;
    protected int timeout;
    protected String appKey;

    private Class<T> interfaceClass;

    protected T referer;

    protected T getReferer() {
        initReferer();
        return referer;
    }

    private void initReferer() {
        try {
            interfaceClass = (Class) Class.forName(interfaceClass.getName(), false, Thread.currentThread().getContextClassLoader());
            //使用动态代理创建出代理对象.
            URL registryUrl = loadRegistryUrl();
            RegistryFactory registryFactory = new ZookeeperRegistryFactory();


            Registry registry = registryFactory.getRegistry(registryUrl);

            //拉取服务列表
            List<URL> serviceUrls = registry.discover(registryUrl);
            //注册当前客户端


            Map<String, String> params = new HashMap<>();

            collectConfigParams(params, protocol, this);
            String path = StringUtils.isBlank(serviceInterface) ? interfaceClass.getName() : serviceInterface;

            String localIp = getLocalHostAddress(Lists.newArrayList(registryUrl));
            //TODO 临时这样处理
            URL refUrl = new URL("motan", localIp, 0, path, params);
            registry.subscribe(refUrl);

            List<Referer> referers = Lists.newArrayList();
            for (URL serviceUrl : serviceUrls) {
                if (serviceUrl.getPath().equals(serviceInterface)) {
                    Referer defaultReferer = new DefaultRpcReferer(serviceUrl);
                    referers.add(defaultReferer);
                    continue;
                }
            }
            Cluster cluster = new DefaultCluster(referers);
            cluster.init();
            Object o = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RefererInvocationHandler(interfaceClass, cluster));
            referer = (T) o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setInterface(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setReferer(T referer) {
        this.referer = referer;
    }
}
