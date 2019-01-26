package com.drake.APPbackground;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.drake.APPbackground.mapper")
@ComponentScan("com.drake.APPbackground.*")
public class ApPbackgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApPbackgroundApplication.class, args);
	}

}

