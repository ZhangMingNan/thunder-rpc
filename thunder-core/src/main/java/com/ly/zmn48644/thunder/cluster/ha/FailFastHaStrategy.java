package com.ly.zmn48644.thunder.cluster.ha;

import com.ly.zmn48644.thunder.cluster.HaStrategy;
import com.ly.zmn48644.thunder.cluster.LoadBalance;
import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

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
