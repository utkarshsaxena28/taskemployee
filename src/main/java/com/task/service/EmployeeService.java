package com.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.Employee;
import com.task.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService 
{
	@Autowired
    private EmployeeRepo repo;
	
	public List<Employee> listAll() 
    {
        return repo.findAll();
    }
	
	public Employee get(Integer id) 
    {
        return repo.findById(id).get();
    }
	
	public void save(Employee emp) 
    {
        repo.save(emp);
    }
	
	public void delete(Integer id) 
    {
        repo.deleteById(id);
    }

}
