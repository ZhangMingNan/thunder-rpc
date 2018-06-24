package com.ly.zmn48644.rpc.transport.netty;

import com.ly.zmn48644.rpc.common.ThunderConstants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //第一版本协议,数据类型选择的范围太大了,存在优化空间
        //int(magic number) + int(type) + long(request id) + int(data length) + body(response,request)
        //获取消息头所标识的消息体字节数组长度
        if (byteBuf.readableBytes() < ThunderConstants.HEADER_LENGTH) {
            throw new RuntimeException("readableBytes error!");
        }
        byteBuf.markReaderIndex();
        //读取消息头数据, 此数据代表 数据长度.
        int magic = byteBuf.readInt();
        if (magic != ThunderConstants.MAGIC) {
            throw new RuntimeException("magic number error!");
        }
        boolean isRequest = byteBuf.readInt() == ThunderConstants.FLAG_REQUEST;
        long requestId = byteBuf.readLong();
        int dataLength = byteBuf.readInt();

        //读取完整的消息体字节数组
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);
        //将字节数组反序列化为java对象(SerializerEngine参考序列化与反序列化章节)
        Object obj = new NettyMessage(isRequest, requestId, data);
        list.add(obj);
    }
}
