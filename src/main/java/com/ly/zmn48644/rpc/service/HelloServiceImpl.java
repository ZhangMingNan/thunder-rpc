package com.ly.zmn48644.rpc.service;

/**
 * Created by zmn on 17-12-22.
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String content) {
        return "hello,"+content;
    }
}
