package ma.example.config;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBSingleton {

 private static MongoDBSingleton mDbSingleton;
 
 private static MongoClient mongoClient;
    
 private static DB db ;
 
 
 private static final String dbHost = "ds019846.mlab.com";
 private static final int dbPort = 19846;
 private static final String dbName = "springtest";
 private static final String dbUser = "anil1883";
 private static final String dbPassword = "anil1883";

 private MongoDBSingleton(){};
 
 public static MongoDBSingleton getInstance(){
  if(mDbSingleton == null){
   mDbSingleton = new MongoDBSingleton();
  }
  return mDbSingleton;
 } 
 
 public DB getTestdb() {
	 
	 
	 
	//link is: mongodb://<user>:<password>@ds<port>.mlab.com:<port>/<db>
	    MongoClientURI uri  = new MongoClientURI("mongodb://anil1883:anil1883@ds019846.mlab.com:19846/springtest");
	    MongoClient client;
		try {
			client = new MongoClient(uri);
		
	    System.out.println(client.getDB("springtest")+"=======================================");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    
  if(mongoClient == null){
   try {
    mongoClient = new MongoClient(dbHost , dbPort);
   } catch (UnknownHostException e) {
    return null;
   }
  }
  if(db == null)
   db = mongoClient.getDB(dbName);
  if(!db.isAuthenticated()){
	  System.out.println("+++++++++++++++++++++++++++++++++++");
   boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
   System.out.println("+++++++++++++++++++++++++++++++++++");
  }
  return db;
 }
}
