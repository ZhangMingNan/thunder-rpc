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


    //反序列化
}
