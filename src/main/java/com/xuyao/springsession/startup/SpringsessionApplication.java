package com.xuyao.springsession.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xuyao.springsession")
@MapperScan("com.xuyao.springsession.dao")
public class SpringsessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsessionApplication.class, args);
	}

}
