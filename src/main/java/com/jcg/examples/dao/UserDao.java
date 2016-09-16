package com.jcg.examples.dao;

import java.sql.SQLException;

import com.jcg.examples.domain.User;

/**
 * @author CENTAUR
 * This interface will be used to communicate with the
 * Database
 */
public interface UserDao
{
		public boolean isValidUser(String username, String password) throws SQLException;
		public boolean processRegistration(User user) throws SQLException ;
}
