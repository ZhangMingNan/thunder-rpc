package org.ming.thunder.cluster;

import org.ming.thunder.protocol.Node;
import org.ming.thunder.rpc.Request;
import org.ming.thunder.rpc.Response;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface Cluster extends Node {

    Response call(Request request);
}
