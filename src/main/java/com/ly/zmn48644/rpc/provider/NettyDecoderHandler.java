package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.serializer.SerializerEngine;
import com.ly.zmn48644.rpc.serializer.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 作者:张明楠(1007350771@qq.com)
 */
public class NettyDecoderHandler extends ByteToMessageDecoder {

    private Class<?> requestClass;

    private SerializerType serializerType;

    public NettyDecoderHandler(Class<?> requestClass, SerializerType serializerType) {
        this.requestClass = requestClass;
        this.serializerType = serializerType;
    }

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //获取消息头所标识的消息体字节数组长度
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();
        //读取消息头数据, 此数据代表 数据长度.
        int dataLength = byteBuf.readInt();
        if (dataLength < 0) {
            channelHandlerContext.close();
        }
        //若当前可以获取到的字节数小于实际长度,则直接返回,直到当前可以获取到的字节数等于实际长度
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        //读取完整的消息体字节数组
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);

        //将字节数组反序列化为java对象(SerializerEngine参考序列化与反序列化章节)
        Object obj = SerializerEngine.deserialize(data, requestClass, serializerType);
        list.add(obj);
    }
}
