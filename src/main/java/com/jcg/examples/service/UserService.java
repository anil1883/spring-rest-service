/**
 *
 */
package com.jcg.examples.service;

import java.sql.SQLException;

import com.jcg.examples.domain.User;

/**
 * @author CENTAUR
 *
 */
public interface UserService {
	public boolean isValidUser(String username, String password) throws SQLException;

	public boolean processRegistration(User user) throws SQLException;
}
