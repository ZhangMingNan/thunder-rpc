package com.ly.zmn48644.thunder.cluster;

import com.ly.zmn48644.thunder.protocol.Node;
import com.ly.zmn48644.thunder.rpc.Request;
import com.ly.zmn48644.thunder.rpc.Response;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface Cluster extends Node {

    Response call(Request request);
}
