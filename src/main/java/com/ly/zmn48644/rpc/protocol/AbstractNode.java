package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.Exporter;

public abstract class AbstractNode implements Exporter {



    @Override
    public void init() {
        boolean result = doInit();


    }



    protected abstract boolean doInit();

}
