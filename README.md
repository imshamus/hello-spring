"# hello-spring" 
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=3000" // will override any setting in applications.properties
mvn spring-boot:run -Dserver.port=3000 // will use 3000 if nothing is set in application.properties, if application.properties is set at 5000, it will use 5000.
latter is automatically applied by Spring Boot, so cliArgs.containsOption("server.port") isn’t needed in this cas

Scenario	                                                                                                            Resulting Port
No port in application.properties and no command-line argument	                                                        8080
No port in application.properties and mvn spring-boot:run -Dserver.port=3000	                                        3000
server.port=5000 in application.properties and mvn spring-boot:run -Dserver.port=3000	                                5000
server.port=5000 in application.properties and mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=3000"	    3000

Explanation of How Spring Boot Prioritizes Configuration
Command-Line Arguments (Passed to Spring Boot Directly): Arguments passed with --server.port=3000 in spring-boot.run.arguments will override application.properties.
Application Properties: Settings in application.properties are applied if not overridden by command-line arguments.
Default Port (8080): If no server.port is defined in application.properties or via command-line arguments, Spring Boot defaults to 8080.
