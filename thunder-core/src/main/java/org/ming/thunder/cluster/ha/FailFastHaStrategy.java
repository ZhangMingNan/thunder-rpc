package org.ming.thunder.cluster.ha;

import org.ming.thunder.cluster.HaStrategy;
import org.ming.thunder.cluster.LoadBalance;
import org.ming.thunder.extension.SpiMeta;
import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
@SpiMeta(name = "failFast")
public class FailFastHaStrategy implements HaStrategy {

    @Override
    public Response call(Request request, LoadBalance loadBalance, List<Referer> referes) {
        return null;
    }
}
