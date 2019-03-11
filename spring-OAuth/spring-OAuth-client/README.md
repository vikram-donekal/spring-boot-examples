
SPRING-OAuth 2.0:

Java - Spring Boot applications: RestFull application (CRUD) on student DTO with  using OAuth2.0.


	Language : java 8
	Framework : Spring Framework.
	Build Tool : Maven
	Container : Docker
	container orchestrator : Kubernetes
	Docker Image (Docker hub ): vikramdonekal/spring-OAuth-client


Running the application:

1: Normal Java-spring boot Application: 

	command : mvn clean compile install spring-boot:run
	It will run Tomcat server with PORT : 9202


2: Docker Image:

	Prerequisite : Docker Should be up and Running.
	
	docker run -d -p 9202:9202 vikramdonekal/spring-OAuth-client
	It will pull image and run the image and expose 9202 of localhost for the services(API calls)
	
                                        
3: Kubernetes :

	Prerequisite : Kubernetes Should be up and Running.
	
	Clone or download my K8s Yaml Files:
	
	Service YAML : kubectl create -f spring_webservice_service.yaml
	Deployment YAML : kubectl create -f spring_webservice_deployment.yaml
	
	OutPut: Pod,service will be created inside Kubernetes.
			U can access API Call using service NODE port
			
			kubectl get service -o wide
			Get the port of service we just deployed.
			
	Sample:  curl:http://locahost:32023/find/all?access_token=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
			

Generating Access Token:

	After Running or Deploying Application. Hit End point of /login

	Generate Authorization  Header using Basic Auth and enter User Id and user Password.


	Sample Request:
	  curl -H 'Authorization: Basic YWRtaW46YWRtaW4=' -H "Content-type: application/json" 'http://localhost:9202/login'

	Sample Response:
   
   
   

		
		
		
    
   
   
	
