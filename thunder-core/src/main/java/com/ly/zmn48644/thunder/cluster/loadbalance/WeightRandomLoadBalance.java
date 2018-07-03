package com.ly.zmn48644.thunder.cluster.loadbalance;

import com.google.common.collect.Lists;
import com.ly.zmn48644.thunder.cluster.AbstractLoadBalance;
import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 软负载加权随机算法实现
 *
 * @author liyebing created on 17/4/23.
 * @version $Id$
 */
@SpiMeta(name = "weightRandom")
public class WeightRandomLoadBalance<T> extends AbstractLoadBalance<T> {

    @Override
    protected Referer<T> doSelect(Request request, List<Referer> referers) {
        //存放加权后的服务提供者列表
        List<Referer> refererList = Lists.newArrayList();
        for (Referer referer : referers) {
            int weight = referer.getWeight();
            for (int i = 0; i < weight; i++) {
                refererList.add(referer);
            }
        }

        int MAX_LEN = refererList.size();
        int index = RandomUtils.nextInt(0, MAX_LEN - 1);
        return refererList.get(index);
    }
}
