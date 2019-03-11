package com.graphql.Graphql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.graphql.Graphql.Exception.StudentNotFoundException;

@Service
public class StudentService {
		
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());	
	public static List<StudentDto> studentList = new ArrayList<>();	
	
	StudentService(){
		
		LOGGER.info("Inside StudentService constructor");
		studentList.add(new StudentDto(1L, "vikram", "donekal"));
		studentList.add(new StudentDto(2L, "sam", "m"));
		studentList.add(new StudentDto(3L, "abc", "def"));
		studentList.add(new StudentDto(4L, "w", "s"));
	}
	
	public Iterable<StudentDto> getAll() {
		
		LOGGER.info("Inside getAll Service");
		return studentList;
	}
	

	public StudentDto getById(Long id) {
		
		LOGGER.info("Inside getById Service");
		StudentDto output=studentList.stream().filter(s -> id==s.getId() ).findAny().orElse(null);		
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		if(optionalStudent.isPresent()){			
			return optionalStudent.get();
		}else{
			throw new StudentNotFoundException(" Student not found to retreive  ",id);
		}	
			
	}
	
	public Iterable<StudentDto> deleteById(Long id) {
		
		LOGGER.info("Inside deleteById Service");
		StudentDto output=studentList.stream().filter(s -> id == s.getId()).findAny().orElse(null);	
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		if(optionalStudent.isPresent()){
			studentList.removeIf(student -> student.getId() == id);	
		}					
		return  studentList;
	}

	public Iterable<StudentDto> insert(StudentDto student) {

		LOGGER.info("Inside insert Service");
		StudentDto output=studentList.stream().filter(s -> student.getFirstName().equals(s.getFirstName())).findAny().orElse(null);	
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		
		if(optionalStudent.isPresent()){
			LOGGER.info("Updating Student");
			update(student);
			
		}else{
			
			studentList.add(new StudentDto(Long.valueOf(studentList.size()),student.getFirstName(),student.getSecondName()));
		}
		return studentList;
	}

	public String  delteAll() {
		
		LOGGER.info("Inside delteAll Service");
		studentList=null;
		return "All Students are Deleted";
	}

	public StudentDto update(StudentDto student) {
		
		LOGGER.info("Inside update Service");
		StudentDto output=studentList.stream().filter(s -> student.getId() == s.getId()).findAny().orElse(null);	
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		
		if(optionalStudent.isPresent()){
			LOGGER.info("Updating Student");
			output.setFirstName(student.getFirstName());
			output.setSecondName(student.getSecondName());
			studentList.stream().filter(s -> student.getId() == s.getId()).forEach(new Consumer<StudentDto>() {
				@Override
				public void accept(StudentDto t) {
					t.setFirstName(student.getFirstName());
					t.setSecondName(student.getSecondName());
				}
			});

			return output;
		}else{
			throw new StudentNotFoundException(" Student not found to delete  ",student.getId());
		}
		
	}

	public Iterable<StudentDto> getStudentByFirstOrLastName(String name) {
	
		LOGGER.info("Inside getStudentByFirstOrLastName Service");
		List<StudentDto> output =studentList.stream().filter(s -> s.getFirstName().contains(name) || s.getSecondName().contains(name)) .collect(Collectors.toList());
		Optional<List<StudentDto>> optionalStudent = Optional.ofNullable(output);
		
		if (optionalStudent.isPresent()){
			return  output;
		}else
		{
			throw new StudentNotFoundException(" Student not found BY first or secondName  ",name);
		}
		
	}

	public Long getTotalNoOfStudents() {
		
		LOGGER.info("Inside getTotalNoOfStudents Service");
		return Long.valueOf(studentList.size());
	}

	

}
