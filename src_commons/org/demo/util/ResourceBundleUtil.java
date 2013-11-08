package org.demo.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceBundleUtil {
	private static Log log = LogFactory.getLog(ResourceBundleUtil.class);

	private static final String DEFAULT_VALUE = "";
	
	
	/**
	 * 根据key获取lable
	 * @param key
	 * @param propertie
	 * @return
	 */
	public String getString(String key,String propertie){
		CompositeConfiguration c = ResourceLoadUtils.getProperties(propertie);
		return c.getString(key);
	}
	
	/**
	 * 
	 * @param locCtxt
	 * @param key
	 * @return
	 */
	public static String getString(LocalizationContext locCtxt, String key,
			Object... parameters) {
		if(!StringUtil.isI18NKey(key)){
			return key;
		}
		
		if (locCtxt == null) {
			return null;
		}
		ResourceBundle bundle = locCtxt.getResourceBundle();

		if (bundle != null) {
			try {
				String message = bundle.getString(key);
				return format(message, locCtxt.getLocale(), parameters);
			}
			catch (Exception mre) {
			}
		}

		return null;
	}
	
	/**
	 * 从ResourceBundle中读取
	 * 
	 * @param resourceBundle
	 * @param key
	 * @param parameters
	 * @return
	 */
	public static String getString(ResourceBundle resourceBundle, String key,
			Object... parameters){
		if(!StringUtil.isI18NKey(key) || resourceBundle == null){
			return key;
		}
		
		String message = null;
		try {
			message = resourceBundle.getString(key);
		}
		catch (Exception e) {
			return key;
		}

		return format(message, resourceBundle.getLocale(), parameters);
	}
	
	private static String format(String message, Locale locale, Object... parameters){
		if (message != null && parameters != null) {
			try{
				MessageFormat formatter = new MessageFormat(message, locale);
				message = formatter.format(parameters);
			}
			catch(Exception e){
				log.error("读取失败", e);
				return DEFAULT_VALUE;
			}
		}
		
		return message;
	}
	
	private static ResourceBundle findMatch(String baseName, Locale pref) {
		try {
			return ResourceBundle.getBundle(baseName, pref, Thread.currentThread().getContextClassLoader());
		}
		catch (Exception mre) {
		}

		return null;
	}
}
