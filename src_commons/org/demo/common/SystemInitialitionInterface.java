package org.demo.common;

import javax.servlet.ServletContextEvent;

public interface SystemInitialitionInterface {
	/**
	 * 初始化
	 * 
	 * @param arg0
	 */
	public void initialized(ServletContextEvent arg0);

	/**
	 * 退出
	 * 
	 * @param arg0
	 */
	public void destroyed(ServletContextEvent arg0);
}
