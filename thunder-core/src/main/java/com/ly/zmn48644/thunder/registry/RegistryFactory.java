package com.ly.zmn48644.thunder.registry;

import com.ly.zmn48644.thunder.rpc.URL;



/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public interface RegistryFactory {
    Registry getRegistry(URL url);
}
