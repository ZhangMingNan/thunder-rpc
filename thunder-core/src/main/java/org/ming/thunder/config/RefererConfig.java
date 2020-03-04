package org.ming.thunder.config;

import com.google.common.collect.Lists;
import org.ming.thunder.cluster.Cluster;
import org.ming.thunder.cluster.DefaultCluster;
import org.ming.thunder.cluster.HaStrategy;
import org.ming.thunder.cluster.LoadBalance;
import org.ming.thunder.common.URLParamType;
import org.ming.thunder.extension.ExtensionLoader;
import org.ming.thunder.proxy.RefererInvocationHandler;
import org.ming.thunder.registry.Registry;
import org.ming.thunder.registry.RegistryFactory;
import org.ming.thunder.rpc.DefaultRpcReferer;
import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.URL;
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

    protected int timeout;

    protected String appKey;

    protected String  ha;

    protected String loadBalance;


    protected Class<T> interfaceClass;

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


            RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getExtension("zookeeper");


            Registry registry = registryFactory.getRegistry(registryUrl);

            //拉取服务列表
            List<URL> serviceUrls = registry.discover(registryUrl);
            //注册当前客户端
            String serviceInterface = interfaceClass.getName();

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

            Cluster cluster = prepareCluster(referers, refUrl);
            Object o = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new RefererInvocationHandler(interfaceClass, cluster));
            referer = (T) o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Cluster prepareCluster(List<Referer> referers, URL url) {
        String haType = url.getParameter(URLParamType.ha.getName(), URLParamType.ha.getValue());
        String loadBalanceType = url.getParameter(URLParamType.loadBalance.getName(), URLParamType.loadBalance.getValue());
        LoadBalance loadbalance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getExtension(loadBalanceType);
        HaStrategy ha = ExtensionLoader.getExtensionLoader(HaStrategy.class).getExtension(haType);
        Cluster cluster = new DefaultCluster(ha, loadbalance, referers);
        cluster.init();
        return cluster;
    }


    public Class<T> getInterface() {
        return interfaceClass;
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

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setReferer(T referer) {
        this.referer = referer;
    }

    public String getHa() {
        return ha;
    }

    public void setHa(String ha) {
        this.ha = ha;
    }

    public String getLoadBalance() {
        return loadBalance;
    }

    public void setLoadBalance(String loadBalance) {
        this.loadBalance = loadBalance;
    }
}
