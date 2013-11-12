package org.demo.plugin.exchange.base;

/**
 * 生命周期抽象类
 * @author Developer
 */
public abstract class AbstractLifeCycle implements ILifeCycle {
    
    /**
     * 默認狀態為未啟動
     */
    protected int state = ILifeCycle.LIFECYCLE_STATE_UNSTART;

    public synchronized void destoryLife() {
        state = ILifeCycle.LIFECYCLE_STATE_UNSTART;
    }

    public synchronized int getState() {
        return state;
    }

    public synchronized void initLife() {
        state = ILifeCycle.LIFECYCLE_STATE_RUNNING;
    }
}
