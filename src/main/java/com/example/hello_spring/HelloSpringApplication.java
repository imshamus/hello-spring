package com.example.hello_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.logging.Logger;

@SpringBootApplication
public class HelloSpringApplication {

	// Initialise java util logger
	private static final Logger logger = Logger.getLogger(HelloSpringApplication.class.getName());

	public static void main(String[] args) 
	{		
		// SpringApplication.run(HelloSpringApplication.class, args);	// simplistic approach

		// manual approach
		SpringApplication app = new SpringApplication(HelloSpringApplication.class);
		// System.out.println("Starting application on port 8080..");	// 8080 is default port
		app.run(args); // pass in args to app

		
		// Example log messages
		logger.info("This is an INFO message");
		logger.warning("This is a WARN message");
		logger.severe("This is a SEVERE/FATAL message");

		
	}

	 // Event listener to log the actual port when the application is fully started
    @Bean
    public ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(WebServerApplicationContext context) {
        return event -> {
            int port = context.getWebServer().getPort();
            System.out.printf("Starting application on port %d\n", port);
        };
    }


	// Bean for CommonsRequestLoggingFilter
	@Bean //configured as bean, managed by spring context (?)
    public CommonsRequestLoggingFilter log()
	{
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter(); 	
		// use of CommonsRequestLoggingFilter which is part of the Spring Framework and is commonly used to 
		// log HTTP request details (like client info and query strings) in web applications
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true); // Optionally include request payload
        loggingFilter.setMaxPayloadLength(10000); // Limit payload length to avoid excessive logs

        return loggingFilter;
    }

}
