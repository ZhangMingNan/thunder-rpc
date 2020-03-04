package org.ming.thunder.rpc;

import java.io.Serializable;

public interface Response extends Serializable {
    long getRequestId();

    Object getValue();

}
