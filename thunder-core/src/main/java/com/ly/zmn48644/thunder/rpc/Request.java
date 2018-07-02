package com.ly.zmn48644.thunder.rpc;

public interface Request {

    long getRequestId();

    String getInterfaceName();

    String getMethodName();

    Object[] getArguments();
}
