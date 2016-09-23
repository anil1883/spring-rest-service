package com.jcg.examples.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jcg.examples.dao.UserDao;
import com.jcg.examples.dao.impl.util.UtilDummy;
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

	public List<User> findAllUsers() {

		System.out.println("sdfbsndfbsndfbnsdbfnsdbn");
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find().sort(new BasicDBObject("id", 1));
		List<User> list = new ArrayList<User>();
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			User user = new User();
			Object obj = o.get("id");
			if(obj instanceof Integer )
				user.setId(((Integer)obj).longValue());
			else 
				user.setId((long)obj);
			user.setUsername((String) o.get("username"));
			user.setAddress((String) o.get("address"));
			user.setEmail((String) o.get("email"));

			list.add(user);
		}
		return list;
	}

	public User findById(long id) {

		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find(new BasicDBObject("id", id));
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			User user = new User();
			Long id2 = (Long) o.get("id");
			if (id2 == id) {
				user.setId(id2.longValue());
				user.setUsername((String) o.get("username"));
				user.setAddress((String) o.get("address"));
				user.setEmail((String) o.get("email"));
				return user;
			}
		}
		return null;
	}

	public User findByName(String name) {

		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find(new BasicDBObject("username", name));
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			User user = new User();
			String username = (String) o.get("id");
			if (name.equalsIgnoreCase(username)) {
				user.setId(((Long) o.get("id")));
				user.setUsername((String) o.get("username"));
				user.setAddress((String) o.get("address"));
				user.setEmail((String) o.get("email"));
				return user;
			}

		}
		return null;

	}

	public void saveUser(User user) {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		user.setId(Long.valueOf(UtilDummy.getNextId("uniqueId")));
		DBCollection coll = db.getCollection("Users");
		BasicDBObject doc = new BasicDBObject("username", user.getUsername()).append("id", user.getId())
				.append("email", user.getEmail()).append("address", user.getAddress());
		coll.insert(doc);
	}

	public void updateUser(User user) {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append("$set", new BasicDBObject("username", user.getUsername()).append("id", user.getId())
				.append("email", user.getEmail()).append("address", user.getAddress()));

		BasicDBObject searchQuery = new BasicDBObject().append("id", user.getId());

		coll.update(searchQuery, newDocument);

	}

	public void deleteUserById(long id) {

		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		DBCursor cursor = coll.find(new BasicDBObject("id", id));
		BasicDBObject query = new BasicDBObject();
		query.append("id", id);
		coll.remove(query);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	public void deleteAllUsers() {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Users");
		BasicDBObject document = new BasicDBObject();
		coll.remove(document);
	}
	

}