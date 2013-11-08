package org.demo.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.demo.util.ResourceLoadUtils;

/**
 * 初始化log4j的配置文件
 * @author huzy
 *
 */
public class Log4jInitialized {
	private static Log logger;
	private static InputStream is = null;
	private static PropertyConfigurator log4jPropertyConfigurator;
    private static Properties properties = new Properties();
    
    
	public static void Initialized() {
		/*URL url  =  Log4jPropertiesInit.class.getResource("../conf/log4j.properties");*/
		is = ResourceLoadUtils.getFromClasspath("conf/log4j.properties");
		try {
			/*properties.load(new InputStreamReader(url.openStream()));*/
			properties.load(is);
			//方式一 PropertyConfigurator.configure(properties);
			//方式二
			log4jPropertyConfigurator = new PropertyConfigurator();
			log4jPropertyConfigurator.doConfigure(properties, LogManager.getLoggerRepository());
			logger = LogFactory.getLog(Log4jInitialized.class);
			logger.info("demo logging configured");
		} catch (IOException e) {
			System.out.println("读取配置文件错误");
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//重载
	public static void reInitialized(){
		is = ResourceLoadUtils.getFromClasspath("conf/log4j.properties");
		try {
			properties.load(is);
			log4jPropertyConfigurator = new PropertyConfigurator();
			log4jPropertyConfigurator.doConfigure(properties, LogManager.getLoggerRepository());
			logger.info("demo logging re-configured");
		} catch (IOException e) {
			System.out.println("读取配置文件错误");
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
