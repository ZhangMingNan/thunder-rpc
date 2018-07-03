package com.ly.zmn48644.thunder.cluster.loadbalance;

import com.ly.zmn48644.thunder.cluster.AbstractLoadBalance;
import com.ly.zmn48644.thunder.cluster.LoadBalance;
import com.ly.zmn48644.thunder.extension.SpiMeta;
import com.ly.zmn48644.thunder.rpc.Referer;
import com.ly.zmn48644.thunder.rpc.Request;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 作者：张明楠
 * 时间：2018/7/2
 */
@SpiMeta(name = "random")
public class RandomLoadBalance<T> extends AbstractLoadBalance<T> {

    @Override
    protected Referer<T> doSelect(Request request, List<Referer> referers) {
        return referers.get(RandomUtils.nextInt(0, referers.size()));
    }
}
