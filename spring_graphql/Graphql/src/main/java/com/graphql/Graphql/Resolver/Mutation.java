package com.graphql.Graphql.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.Graphql.StudentDto;
import com.graphql.Graphql.StudentService;



@Component
public class Mutation  implements GraphQLMutationResolver {

	
	@Autowired
	StudentService studentService;
	
	
	public StudentDto updateStudent(Long id ,String firstName,String  secondName){
		StudentDto input= new StudentDto(id,firstName,secondName);

		return studentService.update(input);
	}
	
	
	public Iterable<StudentDto> insertStudent(String firstName,String  secondName){
		StudentDto input= new StudentDto();
		input.setFirstName(firstName);
		input.setSecondName(secondName);
		
		return studentService.insert(input);
	}
	
	
	public String deleteAllStudents(){

		return studentService.delteAll();
	}
	public Iterable<StudentDto> deleteStudentById(Long id){

		return studentService.deleteById(id);
	}


	
	
	
}
