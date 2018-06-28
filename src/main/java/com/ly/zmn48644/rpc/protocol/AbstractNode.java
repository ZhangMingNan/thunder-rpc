package com.ly.zmn48644.rpc.protocol;

public abstract class AbstractNode implements Node {


    @Override
    public void init() {
        boolean result = doInit();
    }


    protected abstract boolean doInit();

}
