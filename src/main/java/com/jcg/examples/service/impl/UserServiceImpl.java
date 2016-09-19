package com.jcg.examples.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.domain.User;
import com.jcg.examples.service.UserService;

public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

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
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}

}
