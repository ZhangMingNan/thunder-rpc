package org.ming.thunder.registry;

import org.ming.thunder.rpc.URL;



/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public interface RegistryFactory {
    Registry getRegistry(URL url);
}
