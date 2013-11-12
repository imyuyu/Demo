package org.demo.plugin.exchange.util;

import org.demo.plugin.exchange.base.AbstractLifeCycle;
import org.demo.plugin.exchange.base.ILifeCycle;
import org.demo.plugin.exchange.core.ExchangeCoreModule;
import org.demo.plugin.exchange.protocol.ExchangeProtocolModule;
/**
 * 交换工厂
 */
public class ExchangeFactory extends AbstractLifeCycle{
	private ExchangeProtocolModule exchangeProtocolModule;
	private ExchangeCoreModule exchangeCoreModule;
	
	private static ExchangeFactory instance;
	
	private ExchangeFactory(){}
	
	public static ExchangeFactory getInstance(){
		if(instance==null){
			instance = new ExchangeFactory();
			instance.init();
		}
		return instance;
	}
	
	protected void init(){
		exchangeProtocolModule = new ExchangeProtocolModule();
		exchangeCoreModule = new ExchangeCoreModule();
	}

	public ExchangeProtocolModule getExchangeProtocolModule() {
		return exchangeProtocolModule;
	}

	public ExchangeCoreModule getExchangeCoreModule() {
		return exchangeCoreModule;
	}
	
	@Override
	public void destoryLife() {
	    if(this.getState() == ILifeCycle.LIFECYCLE_STATE_RUNNING){
	        super.destoryLife();
	        exchangeCoreModule.destoryLife();
	        exchangeProtocolModule.destoryLife();
	    }
	}
	@Override
    public void initLife() {
        if(this.getState() == ILifeCycle.LIFECYCLE_STATE_UNSTART){
            exchangeProtocolModule = new ExchangeProtocolModule();
            exchangeProtocolModule.initLife();
            exchangeCoreModule.initLife();
            super.initLife();
        }
    }
}
