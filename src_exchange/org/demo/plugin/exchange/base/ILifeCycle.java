package org.demo.plugin.exchange.base;

/**
 * 生命週期接口
 * @author Developer
 *
 */
public interface ILifeCycle {
	public static final int LIFECYCLE_STATE_UNSTART = 0;

	public static final int LIFECYCLE_STATE_RUNNING = 1;

	/**
	 * 初始化生命週期
	 */
	public void initLife();

	/**
	 * 銷毀
	 */
	public void destoryLife();

	/**
	 * 獲取狀態
	 * @return
	 */
	public int getState();

}
