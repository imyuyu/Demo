package org.demo.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.demo.plugin.PluginSystemInit;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

public class PropertyHolderConfigurer extends PropertyPlaceholderConfigurer {
	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	private String fileEncoding;
	
	public void setLocation(Resource location) {
		super.setLocation(location);
		this.setLocations(new Resource[] { location });
	}

	public void setFileEncoding(String encoding) {
		super.setFileEncoding(encoding);
		this.fileEncoding = encoding;
	}
	
	public void setLocations(Resource[] locations) {
		List<Resource> pluginLocations = PluginSystemInit.getInstance().getProperties();
		Collections.addAll(pluginLocations, locations);
		
		Resource[] newLocations = new Resource[pluginLocations.size()];
		newLocations = pluginLocations.toArray(newLocations);
		
		super.setLocations(newLocations);
	}
	/**
	 * 把用户自定义的配置参数合并到主配置中去，前提：自定义的key在主配置中存在
	 * 
	 * @param props
	 */
	public static void mergeCustomProperties(Properties props, String customFile, String fileEncoding) {
		Resource[] customLocations = {new FileSystemResource(customFile)};
			
		Properties customProps = loadProps(customLocations, fileEncoding);
		
		if(customProps != null){
			Set<Map.Entry<Object, Object>> enities = customProps.entrySet();
			for (Map.Entry<Object, Object> entry : enities) {
				if(props.containsKey(entry.getKey())){
					props.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}
	/**
	 * 
	 * @param customLocation
	 * @param fileEncoding
	 * @return
	 */
	public static Properties loadProps(Resource[] locations, String fileEncoding){
		Properties props = new Properties();
		if(locations == null || locations.length == 0){
			return props;
		}
		
		for (Resource customLocation : locations) {
			InputStream is = null;
			try {
				is = customLocation.getInputStream();
				if (customLocation.getFilename().endsWith(XML_FILE_EXTENSION)) {
					propertiesPersister.loadFromXml(props, is);
				}
				else {
					if (fileEncoding != null) {
						propertiesPersister.load(props, new InputStreamReader(is, fileEncoding));
					}
					else {
						propertiesPersister.load(props, is);
					}
				}
			}
			catch (Throwable ex) {
			}
			finally {
				if (is != null) {
					try {
						is.close();
					}
					catch (IOException e) {
					}
				}
			}
		}
		
		return props;
	}
}
