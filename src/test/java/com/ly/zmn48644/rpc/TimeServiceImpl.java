package com.ly.zmn48644.rpc;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class TimeServiceImpl implements TimeService {

    private static final Map<String, String> timeMap = new HashMap<String, String>();

    static {
        timeMap.put("中国", "Asia/Shanghai");
        timeMap.put("美国", "America/Los_Angeles");
    }

    public String getTime(String country) {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss", TimeZone.getTimeZone(timeMap.get(country)));
    }
}
