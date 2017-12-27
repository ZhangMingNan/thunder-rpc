package com.ly.zmn48644.rpc.serializer;

import java.util.HashMap;
import java.util.Map;

public class SerializerEngine {

    private final static Map<SerializerType, ISerializer> serializerMap = new HashMap<SerializerType, ISerializer>();

    static {
        //在静态代码块中完成 serializerMap 的初始化工作
        serializerMap.put(SerializerType.JAVA, new DefaultJavaSerializer());
        //这里还可以扩展其他的...
    }

    //序列化
    public byte[] serialize(Object object, SerializerType serializerType) {
        //获取序列化器
        ISerializer iSerializer = serializerMap.get(serializerType);
        //调用序列化方法
        return iSerializer.serialize(object);
    }

    //反序列化
    public <T> T deserialize(byte[] data, Class<T> clazz,SerializerType serializerType) {
        ISerializer iSerializer = serializerMap.get(serializerType);
        return iSerializer.deserialize(data,clazz);
    }

}
