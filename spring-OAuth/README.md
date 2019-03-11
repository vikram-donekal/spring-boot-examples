
SPRING-OAuth 2.0:

	Java - Spring Boot applications: RestFull application (CRUD) on student DTO with  using OAuth2.0 .

	Language : java 8
	Framework : Spring Framework.
	Build Tool : Maven
	Container : Docker
	container orchestrator : Kubernetes


Docker Image (Docker hub ): 

	vikramdonekal/spring-OAuth-client
	vikramdonekal/spring-OAuth-server
							
							
It starts with a simple, single-provider single-sign on, and works up to a self-hosted OAuth2 Authorization Server with a choice of authentication providers (Facebook or Github).
The samples are all single-page apps using Spring Boot and Spring OAuth on the back end. 



OAuth 2.0 Grant Types:

	1: Authorization Code
	2: Implicit
	3: Password
	4: Client Credentials
	5: Refresh Token

Here in this example i will be used Password and refresh token Grant Types.


Example has two main Components:

	1: Resource  Server (spring-OAuth-client )
	2: Authorization server (spring-OAuth-server) 


Flow Of OAuth (password Grant Type):

	1: Client (PostMan ) will hit   Resource  Server end point  ( /login )  with User id and password.
	2: spring-OAuth-client will tranfer the call to spring-OAuth-server by adding client Details.
	3: spring-OAuth-server will validate the client details and also User Login Details .
	4: spring-OAuth-Server after verfication and validation it will return ACCESS_TOKEN, REFRESH_TOKEN to spring-OAuth-client.
	5: spring-OAuth-client will return deatils as response to Client (Postman ) Call.
	6: Client (postman ) to access any other URI with Resource  Server should pass ACCESS_TOKEN added to Request.
	7: spring-OAuth-client will validate the ACCESS_TOKEN by hitting (/checkToken) endpoint of spring-OAuth-Server.
	8: After validation of ACCESS_TOKEN spring-OAuth-client will allow to use Secure Resources.
	
	


INFO:

https://spring.io/guides/tutorials/spring-boot-oauth2/



	
				
				

							
