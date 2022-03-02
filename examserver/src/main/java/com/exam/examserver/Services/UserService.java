package com.exam.examserver.Services;

import java.util.Set;

import com.exam.examserver.Models.User;
import com.exam.examserver.Models.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
}
