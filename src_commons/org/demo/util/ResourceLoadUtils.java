package org.demo.util;

import java.io.InputStream;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;

/**
 * 
 *
 */
public class ResourceLoadUtils {
	private static Log log = LogFactory.getLog(ResourceLoadUtils.class);
	/**
	 * 
	 */
	public static final String CLASSPATH_PREFIX = "classpath*:";
	
	/**
	 * 读取配置文件
	 * @param path 路径，默认加载classpath*:路径下，path需包含包名
	 * @return
	 */
	public static CompositeConfiguration getProperties(String path) {
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			config.addConfiguration(new PropertiesConfiguration(CLASSPATH_PREFIX+path));
		} catch (ConfigurationException e) {
			log.info("读取文件错误",e);
		}
		return config;
	}
	/**
	 * 从web-info下加载文件
	 * 
	 * @param servletContext
	 * @param location 如：/WEB-INF/conf/***.properties
	 * @return
	 */
	public static InputStream getFromServletContext(ServletContext servletContext,
			String location) {
		if(StringUtil.isBlank(location)){
			return null;
		}
		
		if (!location.startsWith("/")) {
			location = "/" + location;
		}
				
		return servletContext.getResourceAsStream(location);
	}

	/**
	 * 从classpath下加载文件
	 * 
	 * @param location  org/demo/util/***.properties
	 * @return
	 */
	public static InputStream getFromClasspath(String location) {
		if(StringUtil.isBlank(location)){
			return null;
		}
		
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
	}
	
	/**
	 * 自动识别<code>location</code>，如果是以<code>classpath*:</code>开头，则从classpath加载，否则从web-info下加载文件
	 * 
	 * @param servletContext
	 * @param location
	 * @return
	 */
	public static InputStream get(ServletContext servletContext, String location){
		if(StringUtil.isBlank(location)){
			return null;
		}
		
		if(location.startsWith(CLASSPATH_PREFIX)){
			return getFromClasspath(StringUtil.substringAfter(location, CLASSPATH_PREFIX));
		}
		else{
			return getFromServletContext(servletContext, location);
		}
	}
}
