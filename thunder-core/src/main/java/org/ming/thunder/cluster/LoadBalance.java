package org.ming.thunder.cluster;

import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;

import java.util.List;


/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
public interface LoadBalance<T> {

    Referer<T> select(Request request, List<Referer> referers);
}
