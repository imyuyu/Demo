package org.demo.plugin.exchange.protocol.server;

import org.demo.plugin.exchange.exception.ExchangeServerException;

/**
 * exchange 服务
 * <pre>交换中心服务类，所有服务需要继承此接口，并实现service方法</pre>
 * @author Developer
 *
 */
public interface IExchangeServer {
	/**
	 * 根据serviceType提供服务
	 * @param res
	 * @param req
	 */
	public void service(IExchangeServerRequest req, IExchangeServerResponse res) throws ExchangeServerException;

	/**
	 * 服务所能提供的类型
	 * @return
	 */
	public String[] serviceTypes();
}
