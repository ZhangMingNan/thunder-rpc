package com.ly.zmn48644.rpc.provider;

import com.ly.zmn48644.rpc.serializer.SerializerEngine;
import com.ly.zmn48644.rpc.serializer.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 自定义编码器
 */
public class NettyEncoderHandler extends MessageToByteEncoder {

    private SerializerType serializerType;

    public NettyEncoderHandler(SerializerType serializerType) {
        this.serializerType = serializerType;
    }

    protected void encode(ChannelHandlerContext channelHandlerContext, Object input, ByteBuf byteBuf) throws Exception {

        byte[] bytes = SerializerEngine.serialize(input, serializerType);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }
}
