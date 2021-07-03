package com.nguyenhongdang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nguyenhongdang.interceptor.MenuHandleInterceptor;

@SuppressWarnings("deprecation")
@Component
public class MenuInterceptorAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private MenuHandleInterceptor menuInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor);
	}
}
