package com.aop.RestFull_Aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());	
	public static List<StudentDto> studentList = new ArrayList<>();	

	StudentService(){

		LOGGER.info("Inside StudentService Constructor");
		studentList.add(new StudentDto(1, "vikram", "donekal"));
		studentList.add(new StudentDto(2, "sam", "m"));
		studentList.add(new StudentDto(3, "abc", "def"));
		studentList.add(new StudentDto(4, "w", "s"));
	}

	public ResponseEntity<?> getAll() {

		LOGGER.info("Inside getAll Service");
		return new ResponseEntity<>(studentList,HttpStatus.OK);
	}

	public ResponseEntity<?> getById(Integer id) {


		LOGGER.info("Inside getById Service");
		StudentDto output=studentList.stream().filter(s -> id==s.getId() ).findAny().orElse(null);		
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		if(optionalStudent.isPresent()){			
			return new ResponseEntity<>(studentList,HttpStatus.OK);
		}else{
			return new ResponseEntity<>("Student not found",HttpStatus.OK);
		}	

	}

	public ResponseEntity<?> deleteById(Integer id) {

		LOGGER.info("Inside deleteById Service");
		StudentDto output=studentList.stream().filter(s -> id == s.getId()).findAny().orElse(null);	
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);
		if(optionalStudent.isPresent()){
			studentList.removeIf(student -> student.getId() == id);	
		}

		return new ResponseEntity<>(studentList,HttpStatus.OK);
	}

	public ResponseEntity<?> insert(StudentDto student) {

		LOGGER.info("Inside insert Service");
		StudentDto output=studentList.stream().filter(s -> student.getFirstName().equals(s.getFirstName())).findAny().orElse(null);	
		Optional<StudentDto> optionalStudent = Optional.ofNullable(output);

		if(optionalStudent.isPresent()){
			LOGGER.info("Updating Student");
			update(student);

		}else{

			studentList.add(new StudentDto(studentList.size(),student.getFirstName(),student.getSecondName()));
		}
		return new ResponseEntity<>(studentList,HttpStatus.OK);
	}

	public ResponseEntity<?> delteAll() {

		LOGGER.info("Inside delteAll Service");
		studentList=null;
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<?> update(StudentDto student) {

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

			return new ResponseEntity<>(output,HttpStatus.OK);
		}else{
			return new ResponseEntity<>("Student Not found  to Update",HttpStatus.OK);
		}

	}

	public ResponseEntity<?> getStudentByFirstOrLastName(String name) {		

		LOGGER.info("Inside getStudentByFirstOrLastName Service");
		List<StudentDto> output =studentList.stream().filter(s -> s.getFirstName().contains(name) || s.getSecondName().contains(name)) .collect(Collectors.toList());
		Optional<List<StudentDto>> optionalStudent = Optional.ofNullable(output);

		if (optionalStudent.isPresent()){
			return new ResponseEntity<>(output,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>("Student could not found ",HttpStatus.OK);
		}

	}
}
