package org.demo.common;

import java.util.Properties;

import org.demo.plugin.PluginSystemInit;

public class SimpleUrlHandlerMapping extends org.springframework.web.servlet.handler.SimpleUrlHandlerMapping{
	public void setMappings(Properties mappings) {
		Properties pluginUrlMappings = PluginSystemInit.getInstance().getUrlMapping();
		
		mappings.putAll(pluginUrlMappings);
		super.setMappings(mappings);
	}
}
