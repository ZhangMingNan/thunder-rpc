package com.ly.zmn48644.thunder.config.handler;

import com.ly.zmn48644.rpc.Exporter;
import com.ly.zmn48644.rpc.URL;
import com.ly.zmn48644.rpc.Exporter;
import com.ly.zmn48644.rpc.URL;
import com.ly.zmn48644.thunder.rpc.Exporter;
import com.ly.zmn48644.thunder.rpc.URL;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface ConfigHandler {
    <T> Exporter<T> export(Class<T> interfaceClass, T ref, URL registryUrl);

    <T> void unexport(List<Exporter<T>> exporters, URL registryUrl);
}
