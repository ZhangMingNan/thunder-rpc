
package org.ming.thunder.common.threadpool.fixed;


import org.ming.thunder.common.ThunderConstants;
import org.ming.thunder.common.threadpool.AbortPolicyWithReport;
import org.ming.thunder.common.threadpool.AbstractThreadPool;
import org.ming.thunder.common.threadpool.DefaultThreadFactory;
import org.ming.thunder.rpc.URL;

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
