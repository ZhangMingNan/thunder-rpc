
package org.ming.thunder.common.threadpool;


import org.ming.thunder.common.ThunderConstants;
import org.ming.thunder.rpc.URL;
import org.ming.thunder.utils.JVMUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

    private final String threadName;

    private final URL url;

    private static volatile long lastPrintTime = 0;

    private static Semaphore guard = new Semaphore(1);

    public AbortPolicyWithReport(String threadName, URL url) {
        this.threadName = threadName;
        this.url = url;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format("Thread pool is EXHAUSTED!" +
                        " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s), in %s://%s:%d!",
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating(),
                url.getProtocol(), url.getHost(), url.getPort());
        //logger.warn(msg);
        dumpJStack();
        throw new RejectedExecutionException(msg);
    }

    private void dumpJStack() {
        long now = System.currentTimeMillis();

        //dump every 10 minutes
        if (now - lastPrintTime < 10 * 60 * 1000) {
            return;
        }

        if (!guard.tryAcquire()) {
            return;
        }

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                String dumpPath = url.getParameter(ThunderConstants.DUMP_DIRECTORY, System.getProperty("user.home"));

                SimpleDateFormat sdf;

                String OS = System.getProperty("os.name").toLowerCase();

                // window system don't support ":" in file name
                if(OS.contains("win")){
                    sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                }else {
                    sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                }

                String dateStr = sdf.format(new Date());
                FileOutputStream jstackStream = null;
                try {
                    jstackStream = new FileOutputStream(new File(dumpPath, "Dubbo_JStack.log" + "." + dateStr));
                    JVMUtil.jstack(jstackStream);
                } catch (Throwable t) {
//                    logger.error("dump jstack error", t);
                } finally {
                    guard.release();
                    if (jstackStream != null) {
                        try {
                            jstackStream.flush();
                            jstackStream.close();
                        } catch (IOException e) {
                        }
                    }
                }

                lastPrintTime = System.currentTimeMillis();
            }
        });

    }

}
