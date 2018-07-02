package com.ly.zmn48644.thunder.rpc;

import com.ly.zmn48644.thunder.protocol.Node;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public interface Referer<T> extends Node {
    Response call(Request request);
}
