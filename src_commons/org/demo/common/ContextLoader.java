package org.demo.common;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.plugin.PluginSystemInit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public class ContextLoader extends org.springframework.web.context.ContextLoader {
	private WebApplicationContext context;
	@Override
	protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
		Class<?> contextClass = determineContextClass(sc);
		if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
			throw new ApplicationContextException(
					"Custom context class ["+ contextClass.getName() + "] is not of type ["+ ConfigurableWebApplicationContext.class.getName() + "]");
		}

		ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);
		wac.setServletContext(sc);
		String configLocation = sc.getInitParameter(CONFIG_LOCATION_PARAM);
		if (configLocation != null) {
			String[] configLocations = StringUtils.tokenizeToStringArray(configLocation,ConfigurableWebApplicationContext.CONFIG_LOCATION_DELIMITERS);
			/**
			 * 修改点：把插件的XML合并到系统中
			 */
			List<String> pluginConfig = PluginSystemInit.getInstance().getContextConfigLocation();
			if (configLocations != null) {
				Collections.addAll(pluginConfig, configLocations);
			}
			String[] config = new String[pluginConfig.size()];
			config = pluginConfig.toArray(config);

			wac.setConfigLocations(config);
		}
		wac.refresh();
		Object o = wac.getBean("memberController");
		return wac;
	}

	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
			throw new IllegalStateException(
					"Cannot initialize context because there is already a root application context present - check whether you have multiple ContextLoader* definitions in your web.xml!");
		}
		Log logger = LogFactory.getLog(ContextLoader.class);
		long startTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("Root WebApplicationContext: initialization started");
		}
		servletContext.log("Loading Spring root WebApplicationContext");
		try {
			//ApplicationContext parent = loadParentContext(servletContext);

			this.context = this.createWebApplicationContext(servletContext);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,this.context);

			if (logger.isInfoEnabled()) {
				logger.info("Using context class ["+ this.context.getClass().getName()+ "] for root WebApplicationContext");
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Published root WebApplicationContext ["+ this.context+ "] as ServletContext attribute with name ["
						+ WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE+ "]");
			}

			if (logger.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				logger.info("Root WebApplicationContext: initialization completed in "+ elapsedTime + " ms");
			}

			return context;
		} catch (RuntimeException ex) {
			logger.error("Context initialization failed", ex);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,ex);
			throw ex;
		} catch (Error err) {
			logger.error("Context initialization failed", err);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,err);
			throw err;
		}
	}
}
