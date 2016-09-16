package com.jcg.examples.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.domain.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import ma.example.config.MongoDBSingleton;

/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		System.out.println(username + password);
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find(new BasicDBObject("username", username));
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			if (bCryptPasswordEncoder.matches(password, (String) o.get("password")))
				return true;
		}

		return false;
	}

	@Override
	public boolean processRegistration(User user) throws SQLException {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");

		String temp = bCryptPasswordEncoder.encode(user.getPassword());

		BasicDBObject doc = new BasicDBObject("username", user.getUsername()).append("password", temp)
				.append("email", user.getEmail()).append("birth date", user.getBirthDate())
				.append("profession", user.getProfession());
		coll.insert(doc);
		return false;
	}

}