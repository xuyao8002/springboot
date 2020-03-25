package com.xuyao.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebAppConfig {

	@Value("${thread.scheduler.name.prefix}")
	private String threadSchedulerNamePrefix;

	@Value("${thread.scheduler.pool.size}")
	private int threadSchedulerPoolSize;

	@Value("${default.encoding}")
	private String defaultEncoding;

	@Value("${resource.boundle.name}")
	private String resourceBoundleName;

	@Value("${thread.name.prefix}")
	private String threadNamePrefix;

	@Value("${thread.core.pool.size}")
	private int threadCorePoolSize;

	@Value("${thread.max.pool.size}")
	private int threadMaxPoolSize;

	@Value("${thread.queue.capacity}")
	private int threadQueueCapacity;

	@Value("${thread.keep.alive.seconds}")
	private int threadKeepAliveSeconds;

	/**
	 * restTemplate配置
	 * @param factory
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory){
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(15000);
		factory.setReadTimeout(5000);
		return factory;
	}

	/**
	 * 线程池配置
	 * @return
	 */
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(threadSchedulerPoolSize);
		scheduler.setThreadNamePrefix(threadSchedulerNamePrefix);
		return scheduler;
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadCorePoolSize);
		executor.setMaxPoolSize(threadMaxPoolSize);
		executor.setQueueCapacity(threadQueueCapacity);
		executor.setKeepAliveSeconds(threadKeepAliveSeconds);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.setThreadNamePrefix(threadNamePrefix);
		executor.initialize();
		return executor;
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