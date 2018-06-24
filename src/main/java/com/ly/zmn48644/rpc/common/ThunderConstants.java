package com.ly.zmn48644.rpc.common;

public interface ThunderConstants {
    //int(4 magic number) + int(4 type) + long(8 request id) + int(4 data length) + body(response,request)
    int MAGIC = 19901211;
    int FLAG_REQUEST = 1;
    //数据传输协议头部的长度
    int HEADER_LENGTH = 20;
}
