

package org.ming.thunder.rpc;

public interface Exporter<T> {

    Provider<T> getProvider();

    void unexport();

    void init();
}
