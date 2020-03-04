package org.ming.thunder.protocol;


import org.ming.thunder.common.URLParamType;
import org.ming.thunder.extension.ExtensionLoader;
import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.transport.Client;
import org.ming.thunder.transport.EndpointFactory;

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
