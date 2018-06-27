package com.ly.zmn48644.rpc.transport;

import com.ly.zmn48644.rpc.rpc.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/25
 */
public class ProviderMessageRouter implements MessageHandler {

    private Map<String, Provider<?>> providers = new HashMap<String, Provider<?>>();
    @Override
    public Object handle(Object message) {

        return null;
    }

    public <T> void addProvider(Provider<T> provider) {
        //TODO 临时使用 path 作为 servicekey .
        String serviceKey = provider.getUrl().getPath();
        providers.putIfAbsent(serviceKey,provider);

    }
}
