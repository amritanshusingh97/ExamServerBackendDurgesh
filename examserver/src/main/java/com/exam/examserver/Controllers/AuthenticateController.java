package com.exam.examserver.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.Models.JwtRequest;
import com.exam.examserver.Models.JwtResponse;
import com.exam.examserver.Services.UserDetailsServiceImpl;
import com.exam.examserver.config.JwtUtil;

@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	//for generating user received from client
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken (@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
						
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		
		//Above code is executed means user is authenticated
		
		//for getting user details by username
		UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		
		//for generating token for user by JwtUtil class method
		String token= this.jwtUtil.generateToken(userDetails);
		
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}
	
	
	//for authenticating user received from the client
	private void authenticate(String username,String password) throws Exception {
		
		try{
			
		}catch(DisabledException e) {
			e.printStackTrace();
			System.out.println("User Disabled");
			throw new Exception("USER DISABLED");
		}
		catch(BadCredentialsException e) {
			e.printStackTrace();
			System.out.println("Bad Credentials");
			throw new Exception("BAD CREDENTIALS");
		}
		catch(Exception e) {
			e.printStackTrace();
			
			throw new Exception();
		}
		
		
		
	}
	
	
	
}
