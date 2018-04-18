package com.example.imhashvapahversion1.version1.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext CONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ApplicationContextProvider.CONTEXT = context;
	}

	public static Object getBean(Class clazz) {
		return ApplicationContextProvider.CONTEXT.getBean(clazz);
	}
	
	public static Object getBean(String qualifier, Class clazz) {
		return ApplicationContextProvider.CONTEXT.getBean(qualifier , clazz);
	}	
}