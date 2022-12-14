package com.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	public Employee getReferenceByName(String ename);
	
	//public Employee findById(int id);
}
