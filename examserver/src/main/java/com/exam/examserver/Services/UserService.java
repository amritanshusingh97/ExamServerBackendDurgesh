package com.exam.examserver.Services;

import java.util.Set;

import com.exam.examserver.Models.User;
import com.exam.examserver.Models.UserRole;

public interface UserService {

	//posting user to database
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;

	//getting user from database
	public User getUser(String userName);
	
	//delete user from database
	public void deleteUser(Long userId);



}
