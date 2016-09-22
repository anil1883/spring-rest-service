package com.jcg.examples.dao.impl.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import ma.example.config.MongoDBSingleton;

public class UtilDummy {
	
	public static String getNextId(String seq_name) {
	    String sequence_collection = "seq"; // the name of the sequence collection
	    String sequence_field = "seq"; // the name of the field which holds the sequence
	    
	    MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();

	    DBCollection seq = db.getCollection(sequence_collection); // get the collection (this will create it if needed)

	    // this object represents your "query", its analogous to a WHERE clause in SQL
	    DBObject query = new BasicDBObject();
	    query.put("_id", seq_name); // where _id = the input sequence name

	    // this object represents the "update" or the SET blah=blah in SQL
	    DBObject change = new BasicDBObject(sequence_field, 1);
	    DBObject update = new BasicDBObject("$inc", change); // the $inc here is a mongodb command for increment

	    // Atomically updates the sequence field and returns the value for you
	    DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
	    return res.get(sequence_field).toString();
	}

}
