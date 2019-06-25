package com.xuyao.springsession.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xuyao.springsession")
public class SpringsessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsessionApplication.class, args);
	}

}
