

package com.ly.zmn48644.rpc.rpc;

import com.ly.zmn48644.rpc.protocol.Node;

public interface Exporter<T> extends Node {

    Provider<T> getProvider();

    void unexport();
}
