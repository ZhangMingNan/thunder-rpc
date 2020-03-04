package org.ming.thunder.cluster;

import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface HaStrategy {
    Response call(Request request, LoadBalance loadBalance, List<Referer> referes);
}
