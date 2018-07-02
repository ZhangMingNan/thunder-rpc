package com.ly.zmn48644.thunder.cluster.ha;

import com.ly.zmn48644.thunder.cluster.HaStrategy;
import com.ly.zmn48644.thunder.cluster.LoadBalance;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class FailfastHaStrategy implements HaStrategy {

    @Override
    public Response call(Request request, LoadBalance loadBalance) {
        return null;
    }
}
