package org.ming.thunder.transport;

import org.ming.thunder.rpc.DefaultRequest;
import org.ming.thunder.rpc.Provider;
import org.ming.thunder.rpc.Response;

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
        System.out.println(message.toString());
        DefaultRequest defaultRequest = DefaultRequest.class.cast(message);
        Provider provider =  providers.get(defaultRequest.getInterfaceName());
        Response response = provider.call(defaultRequest);

        return response;
    }

    public <T> void addProvider(Provider<T> provider) {
        //TODO 临时使用 path 作为 servicekey .
        String serviceKey = provider.getUrl().getPath();
        providers.putIfAbsent(serviceKey,provider);

    }
}
