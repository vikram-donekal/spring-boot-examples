package com.restfull.Restful_webservice;

public class StudentDto {	

	private int id;
	private String firstName;
	private String secondName;
	
	public int getId() {
		
		return id;
	}
	public void setId(int id) {
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

	
	public StudentDto(int id, String firstName, String secondName) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}

}
	
