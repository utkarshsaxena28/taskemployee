package com.task.security;


import java.util.ArrayList;


import com.task.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.task.repo.EmployeeRepo;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    /*
    @Autowired
    private UserRepo userRepo;*/
	@Autowired
    private EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		final Employee user = employeeRepo.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new User(user.getName(), user.getPassword(), new ArrayList<>());
        }
	}
}
