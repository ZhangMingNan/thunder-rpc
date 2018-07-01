package com.ly.zmn48644.thunder.serialization;

import java.io.IOException;

/**
 * 作者：张明楠
 * 时间：2018/6/23
 */
public interface Serialization {
    //序列化
    byte[] serialize(Object obj) throws IOException;

    //反序列化
    <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException;

}
