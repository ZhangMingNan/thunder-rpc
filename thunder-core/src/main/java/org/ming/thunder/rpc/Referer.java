package org.ming.thunder.rpc;

import org.ming.thunder.protocol.Node;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public interface Referer<T> extends Node {
    Response call(Request request);

    int getWeight();
}
