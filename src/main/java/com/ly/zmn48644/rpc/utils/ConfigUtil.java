package com.ly.zmn48644.rpc.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 作者：张明楠
 * 时间：2018/6/27
 */
public class ConfigUtil {

    /**
     * export 配置的格式: motan:8080
     *
     * @param export
     * @return
     */
    public static Map<String, Integer> parseExport(String export) {
        Map<String, Integer> protocolAndMap = Maps.newHashMap();
        String protocol = StringUtils.substringBefore(export, ":");
        Integer port = Integer.valueOf(StringUtils.substringAfter(export, ":"));
        protocolAndMap.put(protocol, port);
        return protocolAndMap;
    }
}
