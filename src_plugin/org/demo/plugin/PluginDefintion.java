package org.demo.plugin;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;

public class PluginDefintion {
	private Properties pluginProperties = new Properties();
	
	private String id;

	private String name;
	
	private Resource[] properties;
	
	private String[] contextConfigLocation;

	private String[] hbmResources;

	private Properties urlMapping;
	
	private String pluginInitializtion;
	
	private Map<String, List<String>> noCheckURL;
	
	private boolean isAllowCluster = true;

	/**
	 * 是否加载该插件
	 * 
	 * @return true - 启动
	 */
	public boolean isAllowStartup(ServletContext servletContext){	
		return true;
	}

	public String[] getContextConfigLocation() {
		return contextConfigLocation;
	}
	
	/**
	 * 插件的Spring Bean XML
	 */
	public void setContextConfigLocation(String[] contextConfigLocation) {
		this.contextConfigLocation = contextConfigLocation;
	}

	public String[] getHbmResources() {
		return hbmResources;
	}
	
	/**
	 * hibernate的*.hbm.xml文件
	 */
	public void setHbmResources(String[] hbmResources) {
		this.hbmResources = hbmResources;
	}

	public String getId() {
		return id;
	}

	/**
	 * 插件标识
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	/**
	 * 插件显示名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Resource[] getProperties() {
		return properties;
	}
	
	/**
	 * 插件的properties文件
	 */
	public void setProperties(Resource[] properties) {
		this.properties = properties;
	}

	public Properties getUrlMapping() {
		return urlMapping;
	}
	
	public Map<String, List<String>> getNoCheckSessionURL(){
		return this.noCheckURL;
	}

	public String getPluginInitializtion() {
		return pluginInitializtion;
	}

	/**
	 * 设置系统初始化类，直接写class name，需要实现接口<code>org.demo.plugin.PluginSystemInit</code>
	 * 
	 * @param pluginInitializtion
	 */
	public void setPluginInitializtion(String pluginInitializtion) {
		this.pluginInitializtion = pluginInitializtion;
	}
	
	/**
	 * 获取当前插件的配置向，配置文件来自{@link #setProperties(Resource[])}，如果用户自定义（修改）了，包含用户自定义的
	 * 
	 * @param key
	 * @return
	 */
	public final String getPluginProperty(String key){
		return pluginProperties.getProperty(key);
	}
	
	public final void setPluginProperties(Properties props){
		pluginProperties = props;
	}

	public boolean isAllowCluster() {
		return isAllowCluster;
	}

	/**
	 * 该插件是否支持集群环境，如果设置<code>false</code>，将不启动该插件，默认<code>true</code>
	 * @param isAllowCluster
	 */
	public void setAllowCluster(boolean isAllowCluster) {
		this.isAllowCluster = isAllowCluster;
	}

	public Map<String, List<String>> getNoCheckURL() {
		return noCheckURL;
	}

	public void setNoCheckURL(Map<String, List<String>> noCheckURL) {
		this.noCheckURL = noCheckURL;
	}

	public Properties getPluginProperties() {
		return pluginProperties;
	}

	public void setUrlMapping(Properties urlMapping) {
		this.urlMapping = urlMapping;
	}
	
}
