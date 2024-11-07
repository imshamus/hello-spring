package com.example.hello_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) 
	{
		// SpringApplication.run(HelloSpringApplication.class, args);	// simplistic approach

		// manual approach
		SpringApplication app = new SpringApplication(HelloSpringApplication.class);

		System.out.println("Starting application on port 8080..");	// 8080 is default port

		app.run(args);	// pass in args to app

		
	}

	@Bean //configured as bean, managed by spring context (?)
    public CommonsRequestLoggingFilter log()
	{
        CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter(); 	
		// use of CommonsRequestLoggingFilter which is part of the Spring Framework and is commonly used to 
		// log HTTP request details (like client info and query strings) in web applications
        logger.setIncludeClientInfo(true);
        logger.setIncludeQueryString(true);
        return logger;
    }

}
