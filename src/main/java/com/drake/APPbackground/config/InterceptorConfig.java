package com.drake.APPbackground.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.drake.APPbackground.interceptor.MyInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	
	//在此处，将拦截器注册为一个 Bean 
//    @Bean
//    MyInterceptor myInterceptor() {
//        return new MyInterceptor();
//    }
	
	@Autowired
	private MyInterceptor myInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir = registry.addInterceptor(myInterceptor);
		ir.addPathPatterns("/source/**");
		ir.excludePathPatterns("/source/**.html");
	}

}
