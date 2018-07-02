package com.ly.zmn48644.thunder.cluster.ha;

import com.ly.zmn48644.thunder.cluster.HaStrategy;
import com.ly.zmn48644.thunder.cluster.LoadBalance;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public class FailoverHaStrategy implements HaStrategy {

    @Override
    public Response call(Request request, LoadBalance loadBalance) {

        int time = 0;
        int retries = 3;
        Exception exception;
        do {
            try {
                Referer referer = loadBalance.select();
                return referer.call(request);
            } catch (Exception e) {
                exception = e;
                System.out.println("error--->  time:" + time + ",retries:" + retries + ":" + e.getMessage());
            }

        } while (time < retries);

        //TODO 临时这样处理
        throw new RuntimeException(exception);
    }
}
