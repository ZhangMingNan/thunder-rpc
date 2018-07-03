package com.ly.zmn48644.thunder.cluster;

import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/3
 */
public abstract class AbstractLoadBalance<T> implements LoadBalance<T> {

    @Override
    public Referer<T> select(Request request, List<Referer> referers) {
        if (referers == null) {
            throw new RuntimeException("referers is not  null!");
        }
        //如果只有一个可选,就不用调用负载均衡算法.
        if (referers.size() == 0) {
            return referers.get(0);
        } else {
            return doSelect(request, referers);
        }
    }

    protected abstract Referer<T> doSelect(Request request, List<Referer> referers);
}
