package com.task.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.Employee;
import com.task.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
    private EmployeeRepo empRepo;
	
	// Getting list of all employees present in database
	public List<Employee> listAll() {
		List<Employee> list=  (List<Employee>) this.empRepo.findAll();
		return list;
    }
	
	// get Employee by id
	public Employee getEmployeeById(int id) {
		Employee result = empRepo.getReferenceById(id);
		return result;
    }
	
	// Adding the employee or Posting the employee
	public Employee addEmployee(Employee emp) {
		Employee result = empRepo.save(emp);;
		return result;
    }
	
	
	// Update the Employee
	public Employee updateEmp(Employee emp, int Eid) {	
    	emp.setId(Eid);
    	Employee result = empRepo.save(emp);
    	return result;
	}
	
	// Partially Update the Employee
	public Employee partiallyUpdateEmp(Employee emp, int Eid) {	
	    emp.setId(Eid);
	    Employee result = empRepo.save(emp);
	    return result;
	}
	
	// Deleting the Employee
	public void delete(Integer id) {
		empRepo.deleteById(id);
	}

}
