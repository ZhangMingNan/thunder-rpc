package com.ly.zmn48644.rpc;

import com.ly.zmn48644.rpc.framework.ConsumerProxy;
import com.ly.zmn48644.rpc.service.HelloService;

/**
 * Created by zmn on 17-12-22.
 */
public class RpcConsumerMain {
    public static void main(String[] args) {
        ConsumerProxy consumerProxy = new ConsumerProxy();
        HelloService service =  consumerProxy.consume(HelloService.class,"127.0.0.1",8088);
        System.out.println(service.sayHello("zmn"));
    }
}
