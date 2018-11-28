package com.smart.sso.common.config;

import com.netflix.config.DynamicPropertyUpdater;
import com.smart.sso.common.util.SpringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.context.SpringWebContext;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 应用配置工具类
 * 
 * @author Joe
 */
public class ConfigUtils extends PropertyPlaceholderConfigurer {

	private static Properties properties;


	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		super.processProperties(beanFactory, props);
		properties = props;
	}

	public static String getProperty(String name)  {
		try{
			return new String (properties.getProperty(name).getBytes("ISO8859-1"),"utf-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return properties.getProperty(name);
	}

}