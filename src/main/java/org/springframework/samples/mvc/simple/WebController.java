package org.springframework.samples.mvc.simple;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import ma.example.config.Books;
import ma.example.config.MongoDBSingleton;
  
    
@Path("/webservice")  
@Controller
public class WebController { 
	
	@RequestMapping("/helloTest")
	public @ResponseBody String simple() {
		 MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
    	 DB db = dbSingleton.getTestdb();
    	 System.out.println(db+"=============================");
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
             bools.setLikes((String) o.get("likes"));
             list.add(bools);
          }
    	 System.out.println(list.size()+"=============================");
		return "Hello world!";
	}
   
     @GET  
     @Path("/echo/{message}")  
     @Produces("text/plain")  
     public String showMsg(@PathParam("message") String message){  
         return message;      
     }  
       
     
    /* @GET 
     @Path("/insert/{name}/{by}/{likes}/{year}/{description}")  
     @Produces("text/plain")  
     public String insert(@PathParam("name") String name  , @PathParam("description") String description , @PathParam("likes") Long likes , @PathParam("year") String year , @PathParam("by") String by){
    	 MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
    	 DB db = dbSingleton.getTestdb();
    	 DBCollection coll = db.getCollection("Books");	
    	 BasicDBObject doc = new BasicDBObject("title", name).
    			 append("description", description).
    			 append("likes", likes).
    			 append("year", year).
    			 append("by", by);
    	 coll.insert(doc);
    	 return db.isAuthenticated() + " ; " + db.getName();

     }  */
     
     @GET 
     @Path("/getRecords")  
     @Produces(MediaType.APPLICATION_JSON)  
     public List<Books> getRecords(){
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
           //  bools.setLikes((Long) o.get("likes"));
             list.add(bools);
          }
    	 return list;
     } 
     
     @GET 
     @Path("/getRecord/{title}")  
     @Produces(MediaType.APPLICATION_JSON)  
     public List<Books> getRecordFromName(@PathParam("title") String message){
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