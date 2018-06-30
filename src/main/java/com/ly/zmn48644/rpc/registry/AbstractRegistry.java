package com.ly.zmn48644.rpc.registry;

import com.ly.zmn48644.rpc.rpc.URL;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/6/29
 */
public abstract class AbstractRegistry implements Registry ,Closeable{

    @Override
    public void register(URL url) {
        doRegister(url);
    }

    @Override
    public List<URL> discover(URL url) {
        return doDiscover(url);
    }

    @Override
    public void unRegister(URL url) {
        doUnRegister(url);
    }

    @Override
    public void subscribe(URL url) {
        doSubscribe(url);
    }

    @Override
    public void unSubscribe(URL url) {
        doUnSubscribe(url);
    }

    public abstract void doRegister(URL url);

    public abstract List<URL> doDiscover(URL url);


    public abstract void doUnRegister(URL url);


    public abstract void doSubscribe(URL url);


    public abstract void doUnSubscribe(URL url);

    @Override
    public void close() throws IOException {
        doClose();
    }
    protected abstract void doClose();
}
