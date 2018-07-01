package com.ly.zmn48644.thunder.common;

public interface ThunderConstants {

    String DEFAULT_CHARACTER = "utf-8";
    String PATH_SEPARATOR = "/";
    String PROTOCOL_SEPARATOR = "://";
    //int(4 magic number) + int(4 type) + long(8 request id) + int(4 data length) + body(response,request)
    int MAGIC = 19901211;
    int FLAG_REQUEST = 1;
    //数据传输协议头部的长度
    int HEADER_LENGTH = 20;

    //线程池相关
    String THREAD_NAME_KEY = "threadname";
    String DEFAULT_THREAD_NAME = "thunder-rpc";
    String CORE_THREADS_KEY = "cores";
    String DUMP_DIRECTORY = "dump.directory";
    String THREADS_KEY = "threads";
    String QUEUES_KEY = "queues";
    String ALIVE_KEY = "alive";

    int DEFAULT_ALIVE = 60 * 1000;
    int DEFAULT_CORE_THREADS = 0;
    int DEFAULT_QUEUES = 0;
    int DEFAULT_THREADS = 200;
}
