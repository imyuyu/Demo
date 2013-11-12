package org.demo.plugin.exchange;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.plugin.PluginInitialitionInterface;
import org.demo.plugin.exchange.util.ExchangeFactory;

public class ExchangePluginInitializtion implements PluginInitialitionInterface{
	private static final Log log = LogFactory.getLog(ExchangePluginInitializtion.class);
	
	public void initialized(ServletContextEvent arg0) {
		ExchangeFactory.getInstance().initLife();
		System.out.println("exchange plugin initialized success");
	}

	public void destroyed(ServletContextEvent arg0) {
		ExchangeFactory.getInstance().destoryLife();
		System.out.println("exchange plugin destroyed success");
	}

}
