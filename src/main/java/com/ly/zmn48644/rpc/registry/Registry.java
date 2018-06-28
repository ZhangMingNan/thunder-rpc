package com.ly.zmn48644.rpc.registry;

import com.ly.zmn48644.rpc.rpc.URL;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public interface Registry {

    void register(URL url);

    //TODO  临时放在这个接口中.
    List<URL> discover(URL url);
}
