package com.jcg.examples.dao;

import java.sql.SQLException;
import java.util.List;

import com.jcg.examples.domain.User;

/**
 * @author CENTAUR This interface will be used to communicate with the Database
 */
public interface UserDao {
	
	boolean isValidUser(String username, String password) throws SQLException;

	boolean processRegistration(User user) throws SQLException;

	List<User> findAllUsers();

	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	void updateUser(User user);

	void deleteUserById(long id);

	boolean isUserExist(User user);

	void deleteAllUsers();
}
