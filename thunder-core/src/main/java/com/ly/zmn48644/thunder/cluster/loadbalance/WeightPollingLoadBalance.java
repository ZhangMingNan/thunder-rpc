package com.ly.zmn48644.thunder.cluster.loadbalance;

import com.google.common.collect.Lists;
import com.ly.zmn48644.thunder.cluster.AbstractLoadBalance;
import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加权轮询实现负载
 *
 * @param <T>
 */
@SpiMeta(name = "weightPolling")
public class WeightPollingLoadBalance<T> extends AbstractLoadBalance<T> {

    //计数器
    private int index = 0;
    //计数器锁
    private Lock lock = new ReentrantLock();

    @Override
    protected Referer<T> doSelect(Request request, List<Referer> referers) {
        Referer<T> referer = null;
        try {
            lock.tryLock(10, TimeUnit.MILLISECONDS);
            //存放加权后的服务提供者列表
            List<Referer> refererList = Lists.newArrayList();
            for (Referer ref : referers) {
                int weight = ref.getWeight();
                for (int i = 0; i < weight; i++) {
                    refererList.add(ref);
                }
            }
            //若计数大于服务提供者个数,将计数器归0
            if (index >= refererList.size()) {
                index = 0;
            }
            referer = refererList.get(index);
            index++;
            return referer;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        //兜底,保证程序健壮性,若未取到服务,则直接取第一个
        return referers.get(0);
    }
}
