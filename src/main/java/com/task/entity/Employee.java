package com.task.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Employee implements Serializable {
	@Id
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "min 4 required" )
    private String name;
	
	@NotNull
    private int aadhar;
	
	@NotEmpty
	@Size(min = 5, message = "min 5 required" )
    private String address;
	
	@NotEmpty
	@Size(min = 5, message = "min 5 required" )
    private String location;
	
	@Min(value = 40000, message = "Salary must be greater than 40000")
	@Max(value = 100000,message = "Salary must be smaller than 100000")
    private int salary;
    
	@NotEmpty
	private String password;

	public Employee() {
		super();
	}

	public Employee(int id, String name, int aadhar, String address, String location, int salary, String password) {
		super();
		this.id = id;
		this.name = name;
		this.aadhar = aadhar;
		this.address = address;
		this.location = location;
		this.salary = salary;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAadhar() {
		return aadhar;
	}

	public void setAadhar(int aadhar) {
		this.aadhar = aadhar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
