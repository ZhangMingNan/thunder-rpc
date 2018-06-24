package com.ly.zmn48644.rpc.rpc;

public interface Request {

    long getRequestId();

    String getInterfaceName();

    String getMethodName();

    Object[] getArguments();
}
