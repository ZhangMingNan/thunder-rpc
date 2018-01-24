package com.ly.zmn48644.rpc.serializer;

import java.io.*;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class DefaultJavaSerializer implements ISerializer {

    public byte[] serialize(Object object) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(object);

            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
