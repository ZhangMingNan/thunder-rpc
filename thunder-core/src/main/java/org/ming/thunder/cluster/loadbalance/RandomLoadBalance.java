package org.ming.thunder.cluster.loadbalance;

import org.ming.thunder.cluster.AbstractLoadBalance;
import org.ming.thunder.extension.SpiMeta;
import org.ming.thunder.rpc.Referer;
import org.ming.thunder.rpc.Request;
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
