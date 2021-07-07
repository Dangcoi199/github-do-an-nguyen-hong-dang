package com.nguyenhongdang.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.messenger4j.Messenger;

@Configuration
public class WebhookConfig {
	@Bean
	public Messenger messenger(@Value("${messenger4j.pageAccessToken}") String pageAccessToken,
			@Value("${messenger4j.appSecret}") final String appSecret,
			@Value("${messenger4j.verifyToken}") final String verifyToken) {
		return Messenger.create(pageAccessToken, appSecret, verifyToken);
	}
}
