package org.demo.util;

import java.util.Map;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContext;

/**
 * 获取bean的工具类
 * @author Administrator
 *
 */
public class ApplicationContextHolder implements ApplicationContextAware{
	private static ApplicationContext ac = null;
    
    public static Object getBean(String beanName){
    	if(ac == null){
    		return null;
    	}
    	
        try {
        	return ac.getBean(beanName);
		}
		catch (Exception e) {
		}
        
		return null;
    }
    
    public void setApplicationContext(ApplicationContext applicationContext){
    	ac = applicationContext;
    }
    
    public static ApplicationContext getApplicationContext(){
    	return ac;
    }

    /**
     * 根据bean的类型（class、interface、super-class）获取bean
     * @param <T>
     * @param type
     * @return Map Key:bean-id
     */
    @SuppressWarnings("unchecked")
	public <T> Map<String, T> getBeanByType(Class<T> type){
    	return ac.getBeansOfType(type);
    }
}
