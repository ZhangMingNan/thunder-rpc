
package org.ming.thunder.common.threadpool;

import java.util.concurrent.Executor;


public interface ThreadPool {

    Executor getExecutor();

}