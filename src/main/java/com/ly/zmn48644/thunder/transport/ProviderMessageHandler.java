package com.ly.zmn48644.thunder.transport;

import com.ly.zmn48644.rpc.Provider;
import com.ly.zmn48644.rpc.Response;
import com.ly.zmn48644.rpc.Provider;
import com.ly.zmn48644.rpc.Request;
import com.ly.zmn48644.rpc.Response;
import com.ly.zmn48644.thunder.rpc.Provider;
import com.ly.zmn48644.thunder.rpc.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public class ProviderMessageHandler implements MessageHandler {
    protected Map<String, Provider<?>> providers = new HashMap<>();

    @Override
    public Object handle(Object message) {
        Request request = Request.class.cast(message);
        //TODO 获取 key
        String key = null;
        Provider provider = providers.get(key);
        return call(request, provider);
    }

    private Response call(Request request, Provider<?> provider) {
        //TODO 没有处理异常情况,返回异常的 response.
        return provider.call(request);
    }

    public synchronized void addProvider(Provider<?> provider) {
        //TODO 获取 key
        String key = null;
        providers.put(key, provider);
    }

    public synchronized void removeProvider(Provider<?> provider) {
        //TODO 获取 key
        String key = null;
        providers.remove(key);
    }
}
