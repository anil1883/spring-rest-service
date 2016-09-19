/**
 *
 */
package com.jcg.examples.service;

import java.sql.SQLException;
import java.util.List;

import com.jcg.examples.domain.User;

/**
 * @author CENTAUR
 *
 */
public interface UserService {
	public boolean isValidUser(String username, String password) throws SQLException;

	public boolean processRegistration(User user) throws SQLException;
	
    User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
}
