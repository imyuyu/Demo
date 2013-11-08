package org.demo.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.demo.plugin.PluginSystemInit;
import org.springframework.web.context.ContextLoaderListener;

public class SystemInitialized extends ContextLoaderListener{
	private static final Log log = LogFactory.getLog(SystemInitialized.class);
	private static List<SystemInitialitionInterface> initialitions = new ArrayList<SystemInitialitionInterface>();
	@Override
	 protected org.springframework.web.context.ContextLoader createContextLoader() {
		return new org.demo.common.ContextLoader();
	 }

	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		String rootPath = event.getServletContext().getRealPath("");
		System.setProperty("DemoApplicationRoot", rootPath);
		System.setProperty("ApplicationContextPath", "/demo");
		
		Log4jInitialized.Initialized();//注册日志文件
		
		PluginSystemInit pluginSystemInit = PluginSystemInit.getInstance();
		pluginSystemInit.init(event);
		
		
		long startTime = System.currentTimeMillis();
		try {
		      super.contextInitialized(event);
		} catch (Exception e) {
		      log.error("Spring Application Context initialized : ", e);
		      throw new RuntimeException(e);
		}
		log.info("Spring Application Context initialized. 耗时：" + (System.currentTimeMillis() - startTime) + " MS");
		List<String> classes = new ArrayList<String>();
		classes.addAll(pluginSystemInit.getPluginInitializtions());
		
		if ((classes != null) && (!classes.isEmpty())) {
		      for (String className : classes) {
		        startTime = System.currentTimeMillis();
		        try {
		          Object instance = Class.forName(className).newInstance();
		          if ((instance instanceof SystemInitialitionInterface)) {
		            SystemInitialitionInterface initialition = (SystemInitialitionInterface)instance;
		            initialition.initialized(event);
		            initialitions.add(initialition);

		            log.info("System Initialition [" + className + "]. 耗时：" + (System.currentTimeMillis() - startTime) + " MS");
		          }
		          else {
		            log.error("SystemInitialization class [" + className + "] don't implements SystemInitialitionInterface");
		          }
		        }
		        catch (Exception e) {
		          e.printStackTrace();
		        }
		      }

		    }
		log.info("Listener SystemInitialized Initialized OK.");
		System.gc();
	}
}
