package com.ly.zmn48644.rpc;

import com.ly.zmn48644.rpc.framework.ProviderReflect;
import com.ly.zmn48644.rpc.service.HelloService;
import com.ly.zmn48644.rpc.service.HelloServiceImpl;

/**
 * Created by zmn on 17-12-22.
 */
public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service,8088);
    }
}
