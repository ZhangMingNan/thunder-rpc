

package org.ming.thunder.common.threadpool.limited;



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
public class LimitedThreadPool extends AbstractThreadPool {
    /**
     * 可扩容线程池,但是线程池中的数量只会增长不会减少,这种特性可以避免突然的大量任务时,短时间内创建大量线程引起的性能问题.
     * 注意 alive = Long.MAX_VALUE 是关键.
     */
    public LimitedThreadPool(URL url) {
        super(url);
    }

    @Override
    public Executor getExecutor() {
        //线程名称
        String name = url.getParameter(ThunderConstants.THREAD_NAME_KEY, ThunderConstants.DEFAULT_THREAD_NAME);
        //线程数量
        int cores = url.getParameter(ThunderConstants.CORE_THREADS_KEY, ThunderConstants.DEFAULT_CORE_THREADS);
        //最大线程数量
        int threads = url.getParameter(ThunderConstants.THREADS_KEY, ThunderConstants.DEFAULT_THREADS);
        //阻塞队列长度
        int queues = url.getParameter(ThunderConstants.QUEUES_KEY, ThunderConstants.DEFAULT_QUEUES);

        return new ThreadPoolExecutor(cores, threads, Long.MAX_VALUE, TimeUnit.MILLISECONDS,
                createWorkQueue(queues),
                new DefaultThreadFactory(name, true), new AbortPolicyWithReport(name, url));
    }
}


