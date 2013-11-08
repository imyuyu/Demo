package org.demo.plugin.exchange;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.plugin.PluginInitialitionInterface;

public class ExchangePluginInitializtion implements PluginInitialitionInterface{
	private static final Log log = LogFactory.getLog(ExchangePluginInitializtion.class);
	
	public void initialized(ServletContextEvent arg0) {
		System.out.println("exchange plugin initialized success");
	}

	public void destroyed(ServletContextEvent arg0) {
		System.out.println("exchange plugin destroyed success");
	}

}
