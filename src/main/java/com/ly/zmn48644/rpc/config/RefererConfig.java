package com.ly.zmn48644.rpc.config;

import com.ly.zmn48644.rpc.protocol.AbstractNode;
import com.ly.zmn48644.rpc.proxy.RefererInvocationHandler;
import com.ly.zmn48644.rpc.registry.Registry;
import com.ly.zmn48644.rpc.registry.RegistryFactory;
import com.ly.zmn48644.rpc.registry.zookeeper.ZookeeperRegistryFactory;
import com.ly.zmn48644.rpc.rpc.DefaultRpcReferer;
import com.ly.zmn48644.rpc.rpc.Referer;
import com.ly.zmn48644.rpc.rpc.URL;

import java.lang.reflect.Proxy;
import java.util.List;

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
            List<URL> serviceUrls = registry.discover(registryUrl);

            URL url = null;
            for (URL serviceUrl : serviceUrls) {
                if (serviceUrl.getPath().equals(serviceInterface)){
                    url = serviceUrl;
                    break;
                }
            }
            Referer defaultReferer = new DefaultRpcReferer(url);
            AbstractNode abstractNode = (AbstractNode)defaultReferer;
            abstractNode.init();

            Object o = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RefererInvocationHandler(interfaceClass,defaultReferer));
            referer = (T) o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setInterface(Class<T> interfaceClass){
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
