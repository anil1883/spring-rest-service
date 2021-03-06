package org.springframework.samples.mvc.simple;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import ma.example.config.Books;
import ma.example.config.MongoDBSingleton;

@RestController
public class WebController {

	@RequestMapping("/helloTest")
	public @ResponseBody String simple() {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		System.out.println(db + "=============================");
		DBCollection coll = db.getCollection("Books");
		System.out.println(coll + "=============================");
		return "Hello world!";
	}

	@RequestMapping(value="/echo/{message}",method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
	public String showMsg(@PathVariable("message") String message) {
		return message;
	}

	@RequestMapping(value = "/insert/{name}/{by}/{likes}/{year}/{description}", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN)
	public String insert(@PathVariable("name") String name, @PathVariable("description") String description,
			@PathVariable("likes") Long likes, @PathVariable("year") String year, @PathVariable("by") String by) {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Books");
		System.out.println(name);
		BasicDBObject doc = new BasicDBObject("title", name).append("description", description).append("likes", likes)
				.append("year", year).append("by", by);
		coll.insert(doc);
		return "Done";

	}

	@RequestMapping("/getRecords")
	public List<Books> getRecords() {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Books");
		DBCursor cursor = coll.find().sort(new BasicDBObject("by", 1));
		List<Books> list = new ArrayList<Books>();
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			Books bools = new Books();
			bools.setTitle((String) o.get("title"));
			bools.setDescription((String) o.get("description"));
			bools.setYear((String) o.get("year"));
			bools.setBy((String) o.get("by"));
			// bools.setLikes((Long) o.get("likes"));
			list.add(bools);
		}
		return list;
	}

	@RequestMapping(value = "/getRecord/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Books> getRecordFromName(@PathVariable("title") String message) {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("Books");
		DBCursor cursor = coll.find(new BasicDBObject("title", message));
		List<Books> list = new ArrayList<Books>();
		while (cursor.hasNext()) {
			DBObject o = cursor.next();
			Books bools = new Books();
			bools.setTitle((String) o.get("title"));
			bools.setDescription((String) o.get("description"));
			bools.setYear((String) o.get("year"));
			bools.setBy((String) o.get("by"));
			list.add(bools);
		}
		return list;
	}
}