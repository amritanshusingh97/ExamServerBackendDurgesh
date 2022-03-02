package com.exam.examserver.Services.Implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.Models.User;
import com.exam.examserver.Models.UserRole;
import com.exam.examserver.Repository.RoleRepository;
import com.exam.examserver.Repository.UserRepository;
import com.exam.examserver.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = userRepository.findByUsername(user.getUsername());
		
		if(local!=null) {
			System.out.println("User is already there..");
			throw new Exception("User already Present");
		}
		else {
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local=userRepository.save(user);
			
		}
		
		
		
		return local;
	}

}
