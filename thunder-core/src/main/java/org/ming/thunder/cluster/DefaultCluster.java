package org.ming.thunder.cluster;

import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class DefaultCluster implements Cluster {

    private HaStrategy haStrategy;

    private LoadBalance loadBalance;

    List<Referer> referers;

    public DefaultCluster(HaStrategy haStrategy, LoadBalance loadBalance, List<Referer> referers) {
        this.haStrategy = haStrategy;
        this.loadBalance = loadBalance;
        this.referers = referers;
    }

    @Override
    public Response call(Request request) {
        return haStrategy.call(request, loadBalance,referers);
    }

    @Override
    public void init() {
        for (Referer referer : referers) {
            referer.init();
        }
    }
}
