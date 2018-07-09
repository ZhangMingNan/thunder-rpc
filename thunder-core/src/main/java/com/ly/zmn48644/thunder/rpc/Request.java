package com.ly.zmn48644.thunder.rpc;

import java.io.Serializable;

public interface Request extends Serializable {

    long getRequestId();

    String getInterfaceName();

    String getMethodName();

    Object[] getArguments();
}
