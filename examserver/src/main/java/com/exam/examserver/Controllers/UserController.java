package com.exam.examserver.Controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.Models.Role;
import com.exam.examserver.Models.User;
import com.exam.examserver.Models.UserRole;
import com.exam.examserver.Services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	//adding user data to database
	@PostMapping("/")
	public User CreateUser(@RequestBody User user) throws Exception {
		
		Set<UserRole> userRoles = new HashSet<UserRole>();
		Role role = new Role();
		
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoles.add(userRole);
		
//		userService.createUser(user, userRoles);
		
		return userService.createUser(user, userRoles);
		
//		return "Sucess ..";
	}
	
	//getting user from Database
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String userName) {
		
		return userService.getUser(userName);
	}
	
	//delete user by Id
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
	}
	
}
