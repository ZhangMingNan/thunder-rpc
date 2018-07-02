package com.ly.zmn48644.thunder.cluster;

import com.ly.zmn48644.thunder.rpc.Referer;



/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface LoadBalance<T> {

    Referer<T> select();
}
