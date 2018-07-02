package com.ly.zmn48644.thunder.cluster.loadbalance;

import com.ly.zmn48644.thunder.cluster.LoadBalance;
import com.ly.zmn48644.thunder.rpc.Referer;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class RandomLoadBalance<T> implements LoadBalance<T> {

    private List<Referer> referers;

    public RandomLoadBalance(List<Referer> referers) {
        this.referers = referers;
    }

    @Override
    public Referer<T> select() {
        return referers.get(RandomUtils.nextInt(0, referers.size()));
    }
}
