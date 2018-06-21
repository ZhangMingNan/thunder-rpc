package com.ly.zmn48644.rpc.protocol;

import com.ly.zmn48644.rpc.rpc.Provider;
import com.ly.zmn48644.rpc.rpc.URL;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public class DefaultRpcExporter<T> extends AbstractRpcExporter {




    public DefaultRpcExporter(Provider<T> provider, URL url) {


    }

    @Override
    public void unexport() {

    }

    @Override
    protected boolean doInit() {
        //

        return true;
    }

}
