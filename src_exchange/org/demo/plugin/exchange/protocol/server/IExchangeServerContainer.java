package org.demo.plugin.exchange.protocol.server;

/**
 * 服务端管理容器
 * @author Developer
 *
 */
public interface IExchangeServerContainer {

	/**
	 * 获取服务
	 * @param type
	 */
	public IExchangeServer getServer(String type);

	/**
	 * 注册服务端服务
	 * @param server
	 */
	public void regeditServer(IExchangeServer server);

}