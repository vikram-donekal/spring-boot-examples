
SPRING-GraphQL:

	Java - Spring Boot applications: RestFull application (CRUD) on student DTO with  using GraphQL.

	Language : java 8
	Framework : Spring Framework.
	Build Tool : Maven
	Container : Docker
	container orchestrator : Kubernetes
	package manager : Helm (Inside Kubernetes)
	API Documentation : Swagger 2

Docker Image (Docker hub ): 
	
	vikramdonekal/spring-graphql

GraphQL is a query language for APIs and a runtime for fulfilling those queries with your existing data.
   
Here I have integrated SpringBoot with GraphQL  and written a simple  API Calls 
    
	Queries and mutation have been implemented.
	
	GraphQL: https://github.com/facebook/graphql
	
	GraphiQL:  This is a UI that is able to communicate with any GraphQL Server and execute queries and mutations against it. 
	  


Application UseCase:
   
	   CRUD Operation on StudentDto: (id, firstName,secondName)

	   Queries and mutations: GraphQL has single entry point:
			 getAllStudents
			 getStudentById
			 updateStudent
			 insertStudent
			 deleteStudentById
			 deleteAllStudent
		
		
	GraphQL schema : http://localhost:9206/graphql/schema.json
	I used GraphiQL, an Electron app that you can install to test your GraphQL endpoint: http://localhost:9206/graphiql

Running the application:

	1: Normal Java-spring boot Application: 
							command : mvn clean compile install spring-boot:run
		It will run Tomcat server with PORT : 9206
		U can Hit any Api  directly
		example: http://localhost:9206/graphql?query={getAllStudents}

2: Docker Image:

	Prerequisite : Docker Should be up and Running.
	
	docker run -d -p 9206:9206 vikramdonekal/spring-graphql
	It will pull image and run the image and expose 9206 of localhost for the services(API calls)
	
                                        
3: Kubernetes :

	Prerequisite : Kubernetes Should be up and Running.
	
	Clone or download my K8s Yaml Files:
	
	Service YAML : kubectl create -f spring_webservice_service.yaml
	Deployment YAML : kubectl create -f spring_webservice_deployment.yaml
	
	OutPut: Pod,service will be created inside Kubernetes.
			U can access API Call using service NODE port
			
			kubectl get service -o wide
			Get the port of service we just deployed.
			
	Sample:  curl:http://locahost:32023/find/all
			

		
		
		
		
    
   
   
	
