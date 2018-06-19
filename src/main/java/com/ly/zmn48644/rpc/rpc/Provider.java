package com.ly.zmn48644.rpc.rpc;

/**
 * 作者：张明楠
 * 时间：2018/6/18
 */
public interface Provider<T> {

    Class<?> getInterface();

    T getImpl();
}
