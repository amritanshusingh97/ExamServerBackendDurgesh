package com.exam.examserver.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.examserver.Models.User;
import com.exam.examserver.Repository.UserRepository;

//Implementation of UserDetailsService which spring security will internally use 
//for calling UserDetails (basically the user) by passing username as parameter.
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//getting user from userRepository database
		User user = userRepository.findByUsername(username);
		
		if(user==null) {
			System.out.println("User not found with these credentials");
			throw new UsernameNotFoundException("Invalid Credentials i.e username for this user !!!!");
		}
		
		return user;
	}

}
