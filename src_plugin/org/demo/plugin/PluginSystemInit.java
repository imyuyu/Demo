package org.demo.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.common.PropertyHolderConfigurer;
import org.demo.util.ListUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public final class PluginSystemInit {
	private static final Log log = LogFactory.getLog(PluginSystemInit.class);
	private static PluginSystemInit instance;
	private ClassPathXmlApplicationContext context;
	private final static String SystemPluginDefintion = "config.Plugin.path";
	
	private final List<String> pluginIds = new ArrayList<String>();
	private final List<String> contextConfigLocation = new ArrayList<String>();
	private final List<String> hbmResources = new ArrayList<String>();
	private final List<Resource> properties = new ArrayList<Resource>();
	private final Properties urlMapping = new Properties();
	private final List<String> pluginInitializtions = new ArrayList<String>();
	
	public static PluginSystemInit getInstance(){
		if(instance == null){
			instance = new PluginSystemInit();
		}
		return instance;
	}
	
	public void init(ServletContextEvent event){
		long startTime = System.currentTimeMillis();
		String pluginConfPath = event.getServletContext().getInitParameter(SystemPluginDefintion);
		
		context = new ClassPathXmlApplicationContext(pluginConfPath);
		
		String[] pluginBeanNames = context.getBeanNamesForType(org.demo.plugin.PluginDefintion.class);
		if(pluginBeanNames != null){
			for (String defintion : pluginBeanNames) {
				PluginDefintion d = (PluginDefintion)context.getBean(defintion);
				if(d == null){
					continue;
				}
				
				Properties props = PropertyHolderConfigurer.loadProps(d.getProperties(), "UTF-8");
				d.setPluginProperties(props);
				
				
				if(!d.isAllowStartup(event.getServletContext())){
					log.info("插件未启用 : " + d + "");
					continue;
				}
				
				if(pluginIds.contains(d.getId())){
					log.warn("插件[" + d + "]已经存在");
					continue;
				}
				
				pluginIds.add(d.getId());
				
				ListUtil.addAll(contextConfigLocation, d.getContextConfigLocation());
				ListUtil.addAll(hbmResources, d.getHbmResources());
				ListUtil.addAll(properties, d.getProperties());

				
				if(d.getUrlMapping() != null){
					urlMapping.putAll(d.getUrlMapping());
				}
				
				if(d.getPluginInitializtion() != null){
					pluginInitializtions.add(d.getPluginInitializtion());
				}
				
				log.info("发现插件 : " + d.getId()+","+d.getName());
			}
		}
		
		context.destroy();
		
		log.info("扫描Demo插件定义文件完毕. 耗时：" + (System.currentTimeMillis() - startTime) + " MS");
	}
	
	public List<String> getContextConfigLocation() {
		return contextConfigLocation;
	}

	public List<String> getHbmResources() {
		return hbmResources;
	}

	public List<Resource> getProperties() {
		return properties;
	}

	public Properties getUrlMapping() {
		return urlMapping;
	}
	
	public List<String> getPluginIds(){
		return pluginIds;
	}
	/**
	 * 得到所有插件的初始化类
	 * 
	 * @return
	 */
	public List<String> getPluginInitializtions() {
		return pluginInitializtions;
	}
}
