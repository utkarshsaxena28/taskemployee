package com.task.controller;

import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.TaskemployeeApplication;
import com.task.entity.Employee;
import com.task.service.EmployeeService;


@RestController
public class EmployeeController 
{
	static Logger logger = LogManager.getLogger(TaskemployeeApplication.class);
	
	@Autowired
    private EmployeeService employeeService;

	@GetMapping("/employee")
    public List<Employee> list() {
		logger.info("Getting the list of all employees");
		return employeeService.listAll();
    }
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		logger.info("getting Employee By Id...........");
		//return "Employee data "+id;
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/employee")
	public Employee add(@Valid @RequestBody Employee empy) {
		logger.info("Adding New Employee...........");
		return employeeService.addEmployee(empy);
	}
	
	 
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		employeeService.updateEmp(empl,id);
	    System.out.println(empl);
	    return empl;
	}
	
	@PatchMapping("/employee/{id}")
	public Employee updatePartiallye(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		employeeService.partiallyUpdateEmp(empl,id);
	    System.out.println(empl);
	    return empl;
	}
	
	@DeleteMapping("/employee/{id}")
    public void delete(@PathVariable Integer id) {
		employeeService.delete(id);
    }
}
