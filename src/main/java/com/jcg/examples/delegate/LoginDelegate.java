package com.jcg.examples.delegate;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcg.examples.domain.User;
import com.jcg.examples.service.UserService;

public class LoginDelegate {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean isValidUser(String username, String password) throws SQLException {
		return userService.isValidUser(username, password);
	}

	public boolean processRegistration(User user) throws SQLException {
		return userService.processRegistration(user);
	}

}
