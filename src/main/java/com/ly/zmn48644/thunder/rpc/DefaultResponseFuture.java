package com.ly.zmn48644.thunder.rpc;

import com.ly.zmn48644.common.FutureState;
import com.ly.zmn48644.thunder.common.FutureState;


/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public class DefaultResponseFuture implements ResponseFuture {

    private Request request;

    private Object result;
    private int timeout = 0;
    private long createTime = System.currentTimeMillis();
    private Object lock = new Object();


    private FutureState state = FutureState.DOING;

    public DefaultResponseFuture(Request request, int timeout) {
        this.request = request;
        this.timeout = timeout;
    }

    @Override
    public long getRequestId() {
        return request.getRequestId();
    }

    @Override
    public Object getValue() {
        synchronized (lock) {
            //如果执行完成,结果已经返回
            if (!isDoing()) {
                //获取返回结果
                return getValueOrThrowable();
            }
            //执行到这里,说明没有完成返回
            if (timeout <= 0) {
                //没有配置 timeout 所以调用 无参wait 方法.
                try {
                    lock.wait();
                } catch (Exception e) {
//                    cancel(new MotanServiceException(this.getClass().getName() + " getValue InterruptedException : "
//                            + MotanFrameworkUtil.toString(request) + " cost=" + (System.currentTimeMillis() - createTime), e));
                }
                //结果返回, 获取返回结果.
                return getValueOrThrowable();
            } else {
                //执行到这里,说明配置了 timeout
                long waitTime = timeout - (System.currentTimeMillis() - createTime);
                if (waitTime > 0) {
                    //循环到 timeout 时间耗尽
                    for (; ; ) {
                        try {
                            lock.wait(waitTime);
                        } catch (InterruptedException e) {
                        }
                        if (!isDoing()) {
                            //还没有返回 ,跳出循环 .
                            break;
                        } else {
                            //减时间
                            waitTime = timeout - (System.currentTimeMillis() - createTime);
                            if (waitTime <= 0) {
                                break;
                            }
                        }
                    }
                }
                //执行至此 , 判断是否有返回结果, 如果还没有返回结果说明 请求超时.
                if (isDoing()) {

                    timeoutSoCancel();
                }
            }
            return getValueOrThrowable();
        }
    }

    private void timeoutSoCancel() {
        synchronized (lock) {
            if (!isDoing()) {
                return;
            }
            state = FutureState.CANCELLED;
            //TODO 目前不处理
//            exception =
//                    new MotanServiceException(this.getClass().getName() + " request timeout: serverPort=" + serverUrl.getServerPortStr()
//                            + " " + MotanFrameworkUtil.toString(request) + " cost=" + (System.currentTimeMillis() - createTime),
//                            MotanErrorMsgConstant.SERVICE_TIMEOUT);

            lock.notifyAll();
        }

    }

    private Object getValueOrThrowable() {
        //TODO 临时这样处理
        return result;
    }

    public boolean isCancelled() {
        return state.isCancelledState();
    }

    public boolean isDone() {
        return state.isDoneState();
    }

    public boolean isSuccess() {
        return isDone();
        //&& (exception == null);
    }

    private boolean isDoing() {
        return state.isDoingState();
    }

    @Override
    public void onSuccess(Response response) {
        this.result = response.getValue();
        done();
    }

    private boolean done() {
        synchronized (lock) {
            if (!isDoing()) {
                return false;
            }
            state = FutureState.DONE;
            lock.notifyAll();
        }

        return true;
    }

    @Override
    public void onFailure(Response response) {
        //TODO 暂时不处理
        done();
    }
}
