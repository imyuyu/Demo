package org.demo.plugin.exchange.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.plugin.exchange.base.AbstractLifeCycle;
import org.demo.plugin.exchange.base.ILifeCycle;
import org.demo.plugin.exchange.util.ExchangeFactory;

/**
 * 交换中心核心调度模块
 * @author Developer
 */
public class ExchangeCoreModule extends AbstractLifeCycle  {
	private static final Log log = LogFactory.getLog(ExchangeCoreModule.class);

	@Override
	public synchronized void destoryLife() {
		if (this.getState() == ILifeCycle.LIFECYCLE_STATE_RUNNING) {
			super.destoryLife();
		}
	}
	
	@Override
	public synchronized void initLife() {
		if (this.getState() == ILifeCycle.LIFECYCLE_STATE_UNSTART) {
			ExchangeFactory.getInstance().getExchangeProtocolModule().regeditServer(new IExchangeUploadOrgServer());
		}
	}
}
