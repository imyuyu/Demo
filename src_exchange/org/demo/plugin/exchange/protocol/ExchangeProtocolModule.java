package org.demo.plugin.exchange.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.demo.plugin.exchange.base.AbstractLifeCycle;
import org.demo.plugin.exchange.base.ILifeCycle;
import org.demo.plugin.exchange.protocol.server.IExchangeServer;
import org.demo.plugin.exchange.protocol.server.IExchangeServerContainer;

public class ExchangeProtocolModule extends AbstractLifeCycle implements IExchangeServerContainer{
	/**服务存储*/
    private Map<String, IExchangeServer> serverMaps = new ConcurrentHashMap<String, IExchangeServer>();
    
    
    /**
	 * {@inheritDoc}
	 */
	public void regeditServer(IExchangeServer server){
	    String[] types = server.serviceTypes();
	    for(String type:types){
	        serverMaps.put(type, server);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	public IExchangeServer getServer(String type) {
        return serverMaps.get(type);
    }
	
	@Override
    public void destoryLife() {
        if(this.state == ILifeCycle.LIFECYCLE_STATE_RUNNING){
            super.destoryLife();
        }
    }

    @Override
    public void initLife() {
        if(this.state == ILifeCycle.LIFECYCLE_STATE_UNSTART){
            //TransportLayerModule.getInstance().getMulticastChannel().setServer(new ExchangeTransportServer());
            // 获取多播通道
            super.initLife();
        }
    }
}
