package org.ming.thunder.common;

/**
 * 作者：张明楠
 * 时间：2018/6/24
 */
public enum FutureState {
    //等待响应中
    DOING(0),
    //响应完成
    DONE(1),
    CANCELLED(2);
    public final int value;

    FutureState(int value) {
        this.value = value;
    }

    public boolean isCancelledState() {
        return this == CANCELLED;
    }

    public boolean isDoneState() {
        return this == DONE;
    }
    public boolean isDoingState() {
        return this == DOING;
    }
}
