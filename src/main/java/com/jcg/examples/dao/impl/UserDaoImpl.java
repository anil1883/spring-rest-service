package com.jcg.examples.dao.impl;

import java.sql.SQLException;

import com.jcg.examples.dao.UserDao;
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

	@Override
	public boolean isValidUser(String username, String password) throws SQLException {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		System.out.println(username+password);
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find(new BasicDBObject("username", username));
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			if (password.equals(o.get("password")))
				return true;
		}

		return false;
	}

}