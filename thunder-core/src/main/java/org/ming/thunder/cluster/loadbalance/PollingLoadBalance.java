package org.ming.thunder.cluster.loadbalance;

import org.ming.thunder.cluster.AbstractLoadBalance;
import org.ming.thunder.extension.SpiMeta;
import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单轮询实现负载
 *
 * @param <T>
 */
@SpiMeta(name = "polling")
public class PollingLoadBalance<T> extends AbstractLoadBalance<T> {

    //计数器
    private int index = 0;
    private Lock lock = new ReentrantLock();

    @Override
    protected Referer<T> doSelect(Request request, List<Referer> referers) {

        Referer<T> referer = null;
        try {
            lock.tryLock(10, TimeUnit.MILLISECONDS);
            //若计数大于服务提供者个数,将计数器归0
            if (index >= referers.size()) {
                index = 0;
            }
            referer = referers.get(index);
            index++;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        //兜底,保证程序健壮性,若未取到服务,则直接取第一个
        if (referer == null) {
            referer = referers.get(0);
        }
        return referer;
    }
}
