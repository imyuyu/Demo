package org.demo.plugin.exchange.core;

import org.demo.plugin.exchange.base.ISNCEnum;
import org.demo.plugin.exchange.exception.ExchangeServerException;
import org.demo.plugin.exchange.protocol.server.IExchangeServer;
import org.demo.plugin.exchange.protocol.server.IExchangeServerRequest;
import org.demo.plugin.exchange.protocol.server.IExchangeServerResponse;

/**
 * 上传组织机构
 * @author Developer
 *
 */
public class IExchangeUploadOrgServer implements IExchangeServer{
	
	protected String[] serviceTypes = new String[] { ISNCEnum.ExchangeActionEnum.ServiceOrg.getValue() };
	
	public void service(IExchangeServerRequest req, IExchangeServerResponse res)
			throws ExchangeServerException {
		
		
	}
	
	public String[] serviceTypes() {
		return serviceTypes;
	}

}
