package com.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	//public Employee getReferenceByName(String ename);
	public Employee findByName(String name);
	//public Employee findById(int id);
}
