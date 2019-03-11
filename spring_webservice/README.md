
Java - Spring Boot applications: RestFull application (CRUD) on student DTO.

	Language : java 8
	Framework : Spring Framework.
	Build Tool : Maven
	Container : Docker
	container orchestrator : Kubernetes
	package manager : Helm (Inside Kubernetes)
	API Documentation : Swagger 2

Docker Image (Docker hub ): 
		
	   vikramdonekal/spring-restful-webservice

REST (Representational State Transfer)

INFO: https://spring.io/understanding/REST


Application UseCase:
   
	   CRUD Operation on StudentDto: (id, firstName,secondName)

	   APIS:
			GET :/find/all : getAllStudents
			GET : /find/{id} : getStudentById
			POST : /update : updateStudent
			PUT :  /insert : insertStudent
			DELETE : /delete/{id} : deleteStudentById
			DELETE : /delete/all : deleteAllStudent
		

Running the application:

		1: Normal Java-spring boot Application: 
								command : mvn clean compile install spring-boot:run
			It will run Tomcat server with PORT : 9200 
			U can Hit any Api  directly
			example: curl:http://locahost:9200/api/all
			Swagger : http://locahost:9200/swagger-ui.html
	
2: Docker Image:

	Prerequisite : Docker Should be up and Running.
	
	docker run -d -p 9200:9200 vikramdonekal/spring-restful-webservice
	It will pull image and run the image and expose 9200 of localhost for the services(API calls)
	
                                        
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
	
	
You can see API Documentation using swagger-ui.

			

		
		
		
		
    
   
   
	
