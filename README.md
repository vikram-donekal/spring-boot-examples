	Java - Spring Boot applications:

		Language : java 8

		Framework : Spring Framework.

		Build Tool : Maven

		Container : Docker

		container orchestrator : Kubernetes

		package manager : Helm (Inside Kubernetes)

		API Documentation : Swagger 2


Docker Images : (Will be Updating and adding more ) Pick Latest tag always

		 vikramdonekal/spring-restful-webservice

		 vikramdonekal/spring-aop

		 vikramdonekal/spring-graphql
 
 
PORTS:

		9200: Spring_webservice

		9201: Spring_AOP

		9202: Spring-oauth2 client

		9203: Spring-oauth2 Server

		9204: Spring-jwt Client (Will be updating)

		9205: Spring-jwt Server (Will be updating)

		9206: Spring-graphql


Sample Examples:

	1: Spring_webservice:
		Here I have Used  spring boot Rest to show simple  Client-Server/Request-Response.

		   REST (Representational State Transfer)
		   INFO: https://spring.io/understanding/REST

		  INFO: Deatils can be seen inside Project ReadMe file

		 Swagger has Been Integrated.


	2: Spring_AOP:  Aspect oriented programming (AOP)

		   Spring AOP module provides interceptors to intercept an application. 
		   For example, when a method is executed, you can add extra functionality before or after the method execution.

		Here I have used SpringBoot,Spring AOP to show case AOP functionality with my sample Rest API calls

		   Swagger has Been Integrated.

		   INFO AOP Concepts: https://docs.spring.io/spring/docs/2.5.x/reference/aop.html


	3: Spring- GraphQL : A query language for your API.
		   GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data.

		Here I have integrated SpringBoot with GraphQL  and written a simple  API Calls 

			Queries and mutation have been implemented.

			GraphQL: https://github.com/facebook/graphql

			GraphiQL:  This is a UI that is able to communicate with any GraphQL Server and execute queries and mutations against it. 

	4: oauth2:
 
		Here i have created two application auth2 Client and oauth2 server to communicate each other for authentication and authorization using spring framework.

		INFO :
		OAuth 2.0 Authorization Framework  : https://auth0.com/docs/protocols/oauth2


		Spring-oauth2 Client:(Resource Server)

		Here i have Created simple API calls (CRUD) which need Access_token to comuunicate.

		Flow : UI Client hit Spring-oauth2 Client Login Page after authentication and authorization will  get Access Token.
		       Each UI Client request to do API calls to Spring-oauth2 Client(Project) need to pass access_token as authorization.


		Spring-oauth2 Server:

		Here i have Created Spring-oauth2 Server which  does  authentication and authorization provides Access Token, Refresh Token.

		Used : Inmemory Client details and Inmemory UserName,Password just for development usecase.
		   ( you can  use LDAP,DataBase to store user Details for production use )



More to Come:

	1: Spring-JWT
	2: spring Microservice using spring stack .


 
	
		
				  
				  
				  
	
	
	
	
  
