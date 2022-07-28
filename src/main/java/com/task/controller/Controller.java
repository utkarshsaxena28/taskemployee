package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.entity.Employee;
import com.task.service.EmployeeService;


@RestController
public class Controller 
{
	@Autowired
    private EmployeeService service;

	@GetMapping("/employee")
    public List<Employee> list() 
    {
        return service.listAll();
    }

    @PostMapping("/empl")
	 public void add(@RequestBody Employee empy) 
	 {
	    service.save(empy);
	 }

    @DeleteMapping("/employee/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
