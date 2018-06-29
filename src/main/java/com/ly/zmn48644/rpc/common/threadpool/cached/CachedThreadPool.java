
package com.ly.zmn48644.rpc.common.threadpool.cached;


import com.ly.zmn48644.rpc.common.ThunderConstants;
import com.ly.zmn48644.rpc.common.threadpool.AbortPolicyWithReport;
import com.ly.zmn48644.rpc.common.threadpool.AbstractThreadPool;
import com.ly.zmn48644.rpc.common.threadpool.DefaultThreadFactory;
import com.ly.zmn48644.rpc.rpc.URL;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
public class CachedThreadPool extends AbstractThreadPool {
    /**
     * 会缓存一段时间空闲线程的线程池
     * @param url
     */
    public CachedThreadPool(URL url) {
        super(url);
        //线程名称
        name = url.getParameter(ThunderConstants.THREAD_NAME_KEY, ThunderConstants.DEFAULT_THREAD_NAME);
        //线程数
        cores = url.getParameter(ThunderConstants.CORE_THREADS_KEY, ThunderConstants.DEFAULT_CORE_THREADS);
        //最大线程数
        threads = url.getParameter(ThunderConstants.THREADS_KEY, Integer.MAX_VALUE);
        //队列长度
        queues = url.getParameter(ThunderConstants.QUEUES_KEY, ThunderConstants.DEFAULT_QUEUES);
        //空闲线程的最大存存活时间,默认是一分钟.
        alive = url.getParameter(ThunderConstants.ALIVE_KEY, ThunderConstants.DEFAULT_ALIVE);
    }

    @Override
    public Executor getExecutor() {
        //创建执行器
        return new ThreadPoolExecutor(cores, threads, alive, TimeUnit.MILLISECONDS,
                createWorkQueue(queues),
                new DefaultThreadFactory(name, true), new AbortPolicyWithReport(name, url));
    }

}
