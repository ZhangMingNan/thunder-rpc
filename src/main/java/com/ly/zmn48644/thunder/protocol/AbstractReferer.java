package com.ly.zmn48644.thunder.protocol;


import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public abstract class AbstractReferer<T> extends AbstractNode implements Referer<T> {
    @Override
    public Response call(Request request) {
        return doCall(request);
    }

    protected abstract Response doCall(Request request);
}
