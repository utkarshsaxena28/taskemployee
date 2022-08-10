package com.task.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Employee>> list() {

		logger.info("Getting the list of all employees");

		List<Employee> list = employeeService.listAll();

		if (list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/employeeid/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {

		logger.info("getting employee by id number {}..............", id);
		//return "Employee data "+id;

		Employee employee = employeeService.getEmployeeById(id);

		if (employee==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(employee));
	}
	
	@PostMapping("/addemployee")
	public ResponseEntity<Employee> add(@Valid @RequestBody Employee empy) {
		Employee em = null;
		try {
			em = employeeService.addEmployee(empy);
			int id = empy.getId();
			logger.info("Adding New Employee having id equale to {}..........", id);
			return ResponseEntity.of(Optional.of(em));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

/*
	@PostMapping("/login")
	public ResponseEntity<UserDAO> reg(@RequestBody UserDAO usd) {
		try {
			UserDAO u = employeeService.regEmployee(usd);
			int id = usd.getId();
			logger.info("Adding New Employee having id equale to {}..........", id);
			return ResponseEntity.of(Optional.of(u));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}*/
	 
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {

		try {
			Employee emp = employeeService.updateEmp(empl,id);
			logger.info("Updating Employee having id equale to {}............", id);
			return ResponseEntity.ok().body(emp);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PatchMapping("/patchemployee/{id}")
	public ResponseEntity<Employee> updatePartially(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		try {
			Employee emy = employeeService.partiallyUpdateEmp(empl,id);
			logger.info("Updating Employee having id equale to {}............", id);
			return ResponseEntity.of(Optional.of(emy));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {
			employeeService.delete(id);
			logger.info("Employee having id equal to {} is deleted.............", id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
