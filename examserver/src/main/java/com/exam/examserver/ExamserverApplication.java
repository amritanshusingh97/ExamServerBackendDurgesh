package com.exam.examserver;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.examserver.Models.Role;
import com.exam.examserver.Models.User;
import com.exam.examserver.Models.UserRole;
import com.exam.examserver.Services.UserService;


@SpringBootApplication
//@EntityScan(basePackages = {"com.exam.Models"})
public class ExamserverApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*User user = new User();
		
		UserRole userRole = new UserRole();
		
	
		Role role = new Role();
		
		user.setEmail("thakur1997@gmail.com");
		user.setEnabled(true);
		user.setFirstName("Amritanshu");
		user.setLastname("Singh");
		user.setPassword("secret");
		user.setPhone("6684132168");
		user.setUsername("amrit1997");
		
		role.setRoleId(44L);
		role.setRoleName("ADMIN");
		
		userRole.setRole(role);
		userRole.setUser(user);
		
		Set<UserRole> userRoles =new HashSet<UserRole>() ;
		
		userRoles.add(userRole);
		
		userService.createUser(user, userRoles);
		*/
	}

}
