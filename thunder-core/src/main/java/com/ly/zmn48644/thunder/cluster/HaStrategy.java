package com.ly.zmn48644.thunder.cluster;

import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface HaStrategy {
    Response call(Request request, LoadBalance loadBalance, List<Referer> referes);
}
