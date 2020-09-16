package com.linecounter.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ApirestApplication implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/swagger-ui/index.html#/line-counter-controller");

	}

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
