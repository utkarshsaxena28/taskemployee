package com.task.controller;

import java.util.List;


import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.TaskemployeeApplication;
import com.task.entity.Employee;
import com.task.service.EmployeeService;


@RestController
public class EmployeeController {
	
	static Logger logger = LogManager.getLogger(TaskemployeeApplication.class);
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getemployee")
	public List<Employee> list() {
		logger.info("Getting the list of all employees");
		return employeeService.listAll();
	}
	
	@GetMapping("/employeeid/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		logger.info("getting employee by id number {}..............", id);
		//return "Employee data "+id;
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/addemployee")
	public Employee add(@Valid @RequestBody Employee empy) {
		int id = empy.getId();
		logger.info("Adding New Employee having id equale to {}..........", id);
		return employeeService.addEmployee(empy);
	}
	
	 
	@PutMapping("/updateemployee/{id}")
	public Employee updateEmployee(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		employeeService.updateEmp(empl,id);
		int id1 = empl.getId();
		logger.info("Updating Employee having id equale to {}............", id1);
		return empl;
	}
	
	@PatchMapping("/patchemployee/{id}")
	public Employee updatePartiallye(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		employeeService.partiallyUpdateEmp(empl,id);
		int id2 = empl.getId();
		logger.info("Updating Employee having id equale to {}............", id2);
		return empl;
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public void delete(@PathVariable Integer id) {
		logger.info("Employee having id equale to {} is deleted.............", id);
		employeeService.delete(id);
	}	
	
}
