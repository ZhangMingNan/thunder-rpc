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
@SpiMeta(name = "failOver")
public class FailOverHaStrategy implements HaStrategy {

    @Override
    public Response call(Request request, LoadBalance loadBalance, List<Referer> referes) {

        int time = 0;

        int retries = 3;
        Exception exception;
        do {
            try {
                Referer referer = loadBalance.select(request,referes);
                return referer.call(request);
            } catch (Exception e) {
                exception = e;
                e.printStackTrace();
            }
        } while (time < retries);

        //TODO 临时这样处理
        throw new RuntimeException(exception);
    }
}
