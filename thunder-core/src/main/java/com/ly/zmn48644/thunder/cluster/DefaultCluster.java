package com.ly.zmn48644.thunder.cluster;

import com.ly.zmn48644.thunder.cluster.ha.FailoverHaStrategy;
import com.ly.zmn48644.thunder.cluster.loadbalance.RandomLoadBalance;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class DefaultCluster implements Cluster {

    private HaStrategy haStrategy;

    private LoadBalance loadBalance;

    List<Referer> referers;

    public DefaultCluster(List<Referer> referers) {
        this.haStrategy = new FailoverHaStrategy();
        this.loadBalance = new RandomLoadBalance(referers);
        this.referers = referers;
    }

    @Override
    public Response call(Request request) {
        return haStrategy.call(request, loadBalance);
    }

    @Override
    public void init() {
        for (Referer referer : referers) {
            referer.init();
        }
    }
}
