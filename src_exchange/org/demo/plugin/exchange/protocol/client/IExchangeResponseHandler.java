package org.demo.plugin.exchange.protocol.client;


/**
 * 客户端请求-应答的回调
 */
public interface IExchangeResponseHandler {

	/**
	 * 
	 * @param is
	 */
	public void onResponse(IExchangeResponse exchangeResponse);

}