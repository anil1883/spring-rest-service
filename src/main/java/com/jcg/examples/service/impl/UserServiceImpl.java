package com.jcg.examples.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.domain.User;
import com.jcg.examples.service.UserService;

public class UserServiceImpl implements UserService
{

	@Autowired	
	private UserDao userDao;

		public UserDao getUserDao()
		{
				return this.userDao;
		}

		public void setUserDao(UserDao userDao)
		{
				this.userDao = userDao;
		}

		@Override
		public boolean isValidUser(String username, String password) throws SQLException
		{
				return userDao.isValidUser(username, password);
		}

		@Override
		public boolean processRegistration(User user) throws SQLException {
			// TODO Auto-generated method stub
			return userDao.processRegistration(user);
		}

}
