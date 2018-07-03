package com.ly.zmn48644.thunder.cluster.loadbalance;


import com.ly.zmn48644.thunder.cluster.AbstractLoadBalance;
import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.utils.NetUtils;

import java.util.List;

/**
 * 根据调用端 host 地址hash 值实现负载均衡,同调用者的请求将会使用固定的服务提供者.
 *
 * @param <T>
 */
@SpiMeta(name = "hashHost")
public class HashHostLoadBalance<T> extends AbstractLoadBalance<T> {

    @Override
    protected Referer<T> doSelect(Request request, List<Referer> referers) {
        String host = NetUtils.getLocalAddress().getHostAddress();
        int hashCode = host.hashCode();
        int size = referers.size();
        return referers.get(hashCode % size);
    }
}
