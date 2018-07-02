package com.ly.zmn48644.thunder.transport.netty4;

import com.ly.zmn48644.thunder.common.ThunderConstants;
import com.ly.zmn48644.thunder.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage message, ByteBuf byteBuf) throws Exception {
        //按照数据协议拼装
        //int(4 magic number) + int(4 type) + long(8 request id) + int(4 data length) + body(response,request)
        int offset = 0;
        byte[] body = message.getData();
        int bodyLength = body.length;
        int isRequest = message.isRequest() ? ThunderConstants.FLAG_REQUEST : 0;
        long requestId = message.getRequestId();
        //如何拼装二进制数据? 使用数组拷贝
        byte[] data = new byte[ThunderConstants.HEADER_LENGTH + bodyLength];
        byte[] header = new byte[ThunderConstants.HEADER_LENGTH];
        int magic = ThunderConstants.MAGIC;
        //将 magic写入 header 数组
        ByteUtil.int2bytes(magic, header, offset);
        offset += 4;
        ByteUtil.int2bytes(isRequest, header, offset);
        offset += 4;
        ByteUtil.long2bytes(requestId, header, offset);
        offset += 8;
        ByteUtil.int2bytes(bodyLength, header, offset);
        //拷贝头部数据
        System.arraycopy(header, 0, data, 0, ThunderConstants.HEADER_LENGTH);
        //拷贝 body 数据
        System.arraycopy(body, 0, data, ThunderConstants.HEADER_LENGTH, bodyLength);
        byteBuf.writeBytes(data);
    }
}
