package com.drake.APPbackground.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.drake.APPbackground.interceptor.MyInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
		// registry.addResourceHandler("")
	}
	
}
