package com.ouathClient.OuathClient.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ouathClient.OuathClient.DTO.StudentDto;
import com.ouathClient.OuathClient.service.StudentService;

@RestController
public class studentController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/find/all")
	public ResponseEntity<?> getAllStudents(){
		
		LOGGER.info("Inside  getAllStudents Controller ");		
		return studentService.getAll();
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Integer id){
		
		LOGGER.info("Inside  getStudentById Controller ");
		return studentService.getById(id);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> getStudentByFirstOrLastName(@PathVariable String name){
		
		LOGGER.info("Inside  getStudentByFirstOrLastName Controller ");
		return studentService.getStudentByFirstOrLastName(name);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateStudent(@RequestBody StudentDto student){
		
		LOGGER.info("Inside  updateStudent Controller ");
		return studentService.update(student);
	}
	@PutMapping("/insert")
	public ResponseEntity<?> insertStudent(@RequestBody StudentDto student){
		
		LOGGER.info("Inside  insertStudent Controller ");
		return studentService.insert(student);
	}
	@DeleteMapping("/delete/all")
	public ResponseEntity<?> deleteAllStudent(){
		
		LOGGER.info("Inside  deleteAllStudent Controller ");
		return studentService.delteAll();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable Integer id){

		LOGGER.info("Inside  deleteStudentById Controller ");
		return studentService.deleteById(id);
	}
}
