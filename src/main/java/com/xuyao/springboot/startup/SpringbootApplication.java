package com.xuyao.springboot.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@ComponentScan("com.xuyao.springboot")
@MapperScan("com.xuyao.springboot.dao")
@EnableAsync
public class SpringbootApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(SpringbootApplication.class);
		setOtherProperties(app);
		app.run(args);
	}

	private static void setOtherProperties(SpringApplication app) throws IOException {
		Properties properties = new Properties();
		InputStream resourceStream = SpringbootApplication.class.getClassLoader().getResourceAsStream("other.properties");
		properties.load(resourceStream);
		app.setDefaultProperties(properties);
	}

}
