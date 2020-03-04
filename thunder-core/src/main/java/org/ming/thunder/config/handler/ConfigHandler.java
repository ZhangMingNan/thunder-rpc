package org.ming.thunder.config.handler;

import org.ming.thunder.rpc.Exporter;
import org.ming.thunder.rpc.URL;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface ConfigHandler {
    <T> Exporter<T> export(Class<T> interfaceClass, T ref, URL registryUrl);

    <T> void unexport(List<Exporter<T>> exporters, URL registryUrl);
}
