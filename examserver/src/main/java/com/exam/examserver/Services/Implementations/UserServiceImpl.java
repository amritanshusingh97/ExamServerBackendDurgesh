package com.exam.examserver.Services.Implementations;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	//posting user to database
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		
		if(local!=null) {
			System.out.println("User is already there..");
			throw new Exception("User already Present");
		}
		else {
			for(UserRole ur : userRoles) {
				this.roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
		}
		
		return this.userRepository.save(user);
	}

	//getting data from DB by username
	@Override
	public User getUser(String userName) {
		
		return this.userRepository.findByUsername(userName);
	}

	//deleting record from DB using UserID
	@Override
	public void deleteUser(Long userId) {

		 userRepository.deleteById(userId);
		
	}
	
	
	

}
