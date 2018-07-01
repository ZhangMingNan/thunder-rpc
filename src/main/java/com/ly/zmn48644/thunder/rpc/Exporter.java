

package com.ly.zmn48644.thunder.rpc;

public interface Exporter<T> {

    Provider<T> getProvider();

    void unexport();

    void init();
}
