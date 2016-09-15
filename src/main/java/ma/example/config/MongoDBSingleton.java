package ma.example.config;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoURI;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoDBSingleton {

	private static MongoDBSingleton mDbSingleton;

	private static MongoClient mongoClient;

	private static DB db;

	private static final String dbHost = "ds019846.mlab.com";
	private static final int dbPort = 19846;
	private static final String dbName = "springtest";
	private static final String dbUser = "anil1883";
	private static final String dbPassword = "anil1883";

	private MongoDBSingleton() {
	};

	public static MongoDBSingleton getInstance() {
		if (mDbSingleton == null) {
			mDbSingleton = new MongoDBSingleton();
		}
		return mDbSingleton;
	}

	public DB getTestdb() {

		// MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
	/*	MongoURI mongoURI = new MongoURI(
				System.getenv("mongodb://anil1883:anil1883@ds019846.mlab.com:19846/springtest"));*/
		MongoURI mongoURI = new MongoURI("mongodb://anil1883:anil1883@ds019846.mlab.com:19846/springtest");
		
	    //get connected
		try {
			db = mongoURI.connectDB();
			 MongoCredential credential = MongoCredential.createMongoCRCredential(mongoURI.getUsername(), mongoURI.getDatabase(), mongoURI.getPassword());
			 MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
			 mongoClient.setWriteConcern(WriteConcern.JOURNALED);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		    
	    // authenticate
	    // (version 2.7.2) db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());
	   

		/*if (mongoClient == null) {
			try {
				mongoClient = new MongoClient(dbHost, dbPort);
			} catch (UnknownHostException e) {
				return null;
			}
		}
		if (db == null)
			db = mongoClient.getDB(dbName);
		if (!db.isAuthenticated()) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
			System.out.println("+++++++++++++++++++++++++++++++++++"+auth);
		}*/
		return db;
	}
}
