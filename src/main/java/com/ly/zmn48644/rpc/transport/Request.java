package com.ly.zmn48644.rpc.transport;

public interface Request {

    long getRequestId();

    String getInterfaceName();

    String getMethodName();

    Object[] getArguments();
}
