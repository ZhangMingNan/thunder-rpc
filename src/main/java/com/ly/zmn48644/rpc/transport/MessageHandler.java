package com.ly.zmn48644.rpc.transport;

/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public interface MessageHandler {
    Object handle(Object message);
}
