package org.demo.common;

import java.util.List;

import org.demo.plugin.PluginSystemInit;

public class LocalSessionFactoryBean extends org.springframework.orm.hibernate4.LocalSessionFactoryBean{
	public void setMappingResources(String[] mappingResources) {
		
		List<String> pluginMappingResources = PluginSystemInit.getInstance().getHbmResources();
		
		String[] newMappingResources = new String[pluginMappingResources.size() + mappingResources.length];
		
		System.arraycopy(mappingResources, 0, newMappingResources, 0, mappingResources.length);
		
		if(!pluginMappingResources.isEmpty()){
			String[] pluginMappingResources1 = pluginMappingResources.toArray(new String[pluginMappingResources.size()]);
			System.arraycopy(pluginMappingResources1, 0, newMappingResources, mappingResources.length, pluginMappingResources1.length);
		}
		
		super.setMappingResources(newMappingResources);
	}
}
