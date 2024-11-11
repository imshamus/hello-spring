package com.example.hello_spring;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Collections;
import java.util.logging.Logger; // general-purpose logger for application-level events logs internal events, method calls, errors, other signifcant action within my code. Can add logs anywhere in the code using this logger

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

		String port = "8080";
		DefaultApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		// if (cliOpts.containsOption("port")) // Looks for "port" argument (incorrect if you meant server port)			mvn spring-boot:run -Dspring-boot.run.arguments="--port=3000"
		if (cliOpts.containsOption("server.port")) // Checks for the correct "server.port" option and retrieves it		mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=3000"
		{
			port = cliOpts.getOptionValues("port").get(0); // getoptionvalues returns a List<String> since args can be multiple values. get(0) to get first value.
			System.out.println("Setting server port to: " + port);
			app.setDefaultProperties(Collections.singletonMap("server.port", port)); // create small map with a single entry, set "server.port" to the port value that i retrieved

			// System.out.printf("Application started on port %d\n", port);
		}

		app.run(args); // pass in args to app

		
		// Example log messages
		logger.info("This is an INFO message");
		logger.warning("This is a WARN message");
		logger.severe("This is a SEVERE/FATAL message");

		
	}

	 // Event listener to log the actual port when the application is fully started
    /* @Bean
    public ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(WebServerApplicationContext context) {
        return event -> {
            int port = context.getWebServer().getPort();
            System.out.printf("Starting application on port %d\n", port);
        };
    } */


	// Bean for CommonsRequestLoggingFilter
	@Bean //configured as bean, managed by spring context (?)
    public CommonsRequestLoggingFilter log() 
	{
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter(); // specialized filter that automatically logs information about incoming HTTP requests, 
		// such as client information and query strings. It’s especially useful for web applications to track HTTP request details without manually logging every request.

		// use of CommonsRequestLoggingFilter which is part of the Spring Framework and is commonly used to 
		// log HTTP request details (like client info and query strings) in web applications
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true); // Optionally include request payload
        loggingFilter.setMaxPayloadLength(10000); // Limit payload length to avoid excessive logs

        return loggingFilter;
    }

}
