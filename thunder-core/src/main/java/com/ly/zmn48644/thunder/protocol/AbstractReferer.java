package com.ly.zmn48644.thunder.protocol;


import com.ly.zmn48644.thunder.common.URLParamType;
import com.ly.zmn48644.thunder.extension.ExtensionLoader;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;
import com.ly.zmn48644.thunder.rpc.URL;
import com.ly.zmn48644.thunder.transport.Client;
import com.ly.zmn48644.thunder.transport.EndpointFactory;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public abstract class AbstractReferer<T> extends AbstractNode implements Referer<T> {

    protected EndpointFactory endpointFactory;
    protected Client client;
    protected int weight;

    public int getWeight() {
        return weight;
    }

    public AbstractReferer(URL url) {
        this.endpointFactory = ExtensionLoader.getExtensionLoader(EndpointFactory.class).getExtension("netty");
        this.client = endpointFactory.createClient(url);
        weight = url.getParameter(URLParamType.weight.getName(), Integer.parseInt(URLParamType.weight.getValue()));
    }

    @Override
    public void init() {
        doInit();
    }

    @Override
    public Response call(Request request) {
        return doCall(request);
    }

    protected abstract Response doCall(Request request);
}
