package com.ly.zmn48644.thunder.rpc;

import java.io.Serializable;

public interface Response extends Serializable {
    long getRequestId();

    Object getValue();

}
