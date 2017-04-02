package com.sungan.ad.commons;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 说明:
 * 
 * @date 2016年12月28日 上午1:12:53
 * @version V1.1
 */
@Component
public class SpringApplicationContextUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext applicationContext(){
		return applicationContext;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringApplicationContextUtil.applicationContext = applicationContext;
	}

}
