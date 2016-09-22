package com.jcg.examples.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.domain.User;
import com.jcg.examples.service.UserService;

public class UserServiceImpl implements UserService {

	private static final AtomicInteger counter = new AtomicInteger();


	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		return userDao.isValidUser(username, password);
	}

	@Override
	public boolean processRegistration(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.processRegistration(user);
	}

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public User findById(long id) {
		return userDao.findById(id);

	}

	public User findByName(String name) {
		return userDao.findByName(name);
	}

	public void saveUser(User user) {
		//user.setId(counter.incrementAndGet());
		userDao.saveUser(user);
	}

	public void updateUser(User user) {
		//int index = users.indexOf(user);
		//users.set(index, user);
	}

	public void deleteUserById(long id) {

		userDao.deleteUserById(id);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	public void deleteAllUsers() {
		userDao.deleteAllUsers();
	}

	

}
