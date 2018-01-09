package com.ly.zmn48644.rpc.invoker.proxy;

import com.ly.zmn48644.rpc.model.RpcRequest;
import com.ly.zmn48644.rpc.model.RpcResponse;
import com.ly.zmn48644.rpc.invoker.InvokerResponseHolder;
import com.ly.zmn48644.rpc.invoker.InvokerServiceCallable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokerInvocationHandler implements InvocationHandler {

    private Class<?> targetService;


    private ExecutorService executorService = null;


    public InvokerInvocationHandler(Class<?> targetService) {
        this.targetService = targetService;
        //创建固定容量的线程池
        executorService = Executors.newFixedThreadPool(10);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return null;
        }

        String targetServiceName = targetService.getName();

        //从配置中心获取到服务提供者IP和端口数据
        System.out.println("代理执行" + targetServiceName + "接口的" + method.getName());

        //创建客户端线程组
        //这里先写死
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8081);

        RpcRequest request = new RpcRequest();
        //设置请求ID
        request.setRequestId(UUID.randomUUID().toString() + "-" + Thread.currentThread().getId());
        request.setMethod(method.getName());
        request.setService(targetServiceName);
        request.setObjects(args);
        InvokerResponseHolder.initResponseData(request.getRequestId());

        Future<RpcResponse> future = executorService.submit(new InvokerServiceCallable(request,address));

        return future.get().getResult();
    }


}























