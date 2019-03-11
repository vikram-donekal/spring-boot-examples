package com.graphql.Graphql;

import org.springframework.stereotype.Component;

@Component
public class StudentDto {	

	private Long id;
	private String firstName;
	private String secondName;
	
	public Long getId() {
		
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Override
	public String toString() {
		String value="Student  id : "+this.getId() + "\nStudent FirstName: "+this.getFirstName()+"\nStudent LastName: "+this.getSecondName();
		return value;
	}

	
	public StudentDto(Long id2, String firstName, String secondName) {
		this.id = id2;
		this.firstName = firstName;
		this.secondName = secondName;
	}
	public StudentDto() {
	}
	

}
	
