

package com.ly.zmn48644.rpc.rpc;

public interface Exporter<T> {

    Provider<T> getProvider();

    void unexport();
}
