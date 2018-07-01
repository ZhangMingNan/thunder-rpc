
package com.ly.zmn48644.thunder.common.threadpool.fixed;



import com.ly.zmn48644.thunder.common.ThunderConstants;
import com.ly.zmn48644.thunder.common.threadpool.AbortPolicyWithReport;
import com.ly.zmn48644.thunder.common.threadpool.AbstractThreadPool;
import com.ly.zmn48644.thunder.common.threadpool.DefaultThreadFactory;
import com.ly.zmn48644.thunder.rpc.URL;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：张明楠
 * 时间：2018/6/26
 */
public class FixedThreadPool extends AbstractThreadPool {
    /**
     * 固定线程数量的线程池
     */
    public FixedThreadPool(URL url) {
        super(url);
        //线程名称
        name = url.getParameter(ThunderConstants.THREAD_NAME_KEY, ThunderConstants.DEFAULT_THREAD_NAME);
        //固定的线程数量
        threads = url.getParameter(ThunderConstants.THREADS_KEY, ThunderConstants.DEFAULT_THREADS);
        //队列长度
        queues = url.getParameter(ThunderConstants.QUEUES_KEY, ThunderConstants.DEFAULT_QUEUES);
    }

    @Override
    public Executor getExecutor() {
        return new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                createWorkQueue(queues),
                new DefaultThreadFactory(name, true), new AbortPolicyWithReport(name, url));
    }


}
