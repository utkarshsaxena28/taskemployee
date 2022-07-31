package com.task.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.entity.Employee;
import com.task.service.EmployeeService;


@RestController
public class EmployeeController 
{
	@Autowired
    private EmployeeService employeeService;

	@GetMapping("/employee")
    public List<Employee> list() {
        return employeeService.listAll();
    }

    @GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {
		System.out.print("Controlelr calling...");
		return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee")
	public Employee add(@Valid @RequestBody Employee empy) {
		return employeeService.addEmployee(empy);
	}
 
	@PutMapping("/employee/{id}")
	public Employee updateEmp(@Valid @RequestBody Employee empl, @PathVariable("id") int id) {
		employeeService.updateEmp(empl,id);
	    System.out.println(empl);
	    return empl;
	}

    @DeleteMapping("/employee/{id}")
    public void delete(@Valid @PathVariable Integer id) {
        employeeService.delete(id);
    }
}
