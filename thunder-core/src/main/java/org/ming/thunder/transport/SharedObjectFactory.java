package org.ming.thunder.transport;

/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public interface SharedObjectFactory<T> {
    T makeObject();
}
