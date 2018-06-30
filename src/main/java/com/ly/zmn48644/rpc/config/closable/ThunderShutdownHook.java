package com.ly.zmn48644.rpc.config.closable;


import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 作者：张明楠
 * 时间：2018/6/29
 */
public class ThunderShutdownHook extends Thread {


    private static final ThunderShutdownHook hook = new ThunderShutdownHook("shutdown-hook-thread");

    private static List<Closeable> resources = new ArrayList<>();

    //保证只会被调用一次
    private final AtomicBoolean destroyed;

    private ThunderShutdownHook() {
        this.destroyed = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        destroyAll();
    }

    private void destroyAll() {

        //如果已经被调用过一次,直接返回
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }
        //释放
        for (Closeable resource : resources) {
            try {
                resource.close();
            } catch (IOException e) {
                //TODO 异常打印日志!
            }
        }
        if (resources.size() != 0) {
            resources.clear();
        }
        System.out.println("----开始释放资源!");
    }

    /**
     * 关闭虚拟机时,用于资源释放!
     *
     * @param name
     */
    public ThunderShutdownHook(String name) {
        super(name);
        this.destroyed = new AtomicBoolean(false);
    }

    public synchronized static void register(Closeable closeable){
        resources.add(closeable);
    }

    public static ThunderShutdownHook getShutdownHook() {
        return hook;
    }
}
