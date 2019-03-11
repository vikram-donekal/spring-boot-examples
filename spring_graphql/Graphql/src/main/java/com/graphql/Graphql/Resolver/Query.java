package com.graphql.Graphql.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.Graphql.StudentDto;
import com.graphql.Graphql.StudentService;

@Component
public class Query implements GraphQLQueryResolver{

	@Autowired
	StudentService studentService;


	public Iterable<StudentDto> getAllStudents	(){

		return studentService.getAll();
	}
	public StudentDto getStudentById(Long id){

		return studentService.getById(id);
	}

	public Iterable<StudentDto> getStudentByFirstOrLastName(String name){

		return studentService.getStudentByFirstOrLastName(name);
	}
	
	public Long getTotalCountOfStudents(){
		return studentService.getTotalNoOfStudents();
		
	}

	

}
