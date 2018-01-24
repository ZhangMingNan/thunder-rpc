package com.ly.zmn48644.rpc.serializer;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public interface ISerializer {
    /**
     * 序列化
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
