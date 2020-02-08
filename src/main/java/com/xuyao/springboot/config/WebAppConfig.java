package com.xuyao.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class WebAppConfig {

	@Value("${thread.name.prefix}")
	private String threadNamePrefix;

	@Value("${thread.pool.size}")
	private int threadPoolSize;

	@Value("${default.encoding}")
	private String defaultEncoding;

	@Value("${resource.boundle.name}")
	private String resourceBoundleName;

	/**
	 * 线程池配置
	 * @return
	 */
	@Bean
	public ThreadPoolTaskScheduler scheduledExecutorService() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(threadPoolSize);
		scheduler.setThreadNamePrefix(threadNamePrefix);
		return scheduler;
	}

	/**
	 * 国际化配置
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(resourceBoundleName.split(","));
        messageSource.setDefaultEncoding(defaultEncoding);
        return messageSource;
    }

}