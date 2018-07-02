package com.ly.zmn48644.thunder.common.threadpool;

import com.ly.zmn48644.thunder.rpc.URL;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 作者：张明楠
 * 时间：2018/6/28
 */
public abstract class AbstractThreadPool implements ThreadPool {
    protected URL url;

    protected String name;
    //线程数
    protected int cores;
    //最大线程数
    protected int threads;
    //队列长度
    protected int queues;
    //空闲线程的存活时间
    protected int alive;

    public AbstractThreadPool(URL url) {
        this.url = url;
    }

    protected BlockingQueue<Runnable> createWorkQueue(int queues) {
        return queues == 0 ? new SynchronousQueue<Runnable>() :
                (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                        : new LinkedBlockingQueue<Runnable>(queues));
    }
}
