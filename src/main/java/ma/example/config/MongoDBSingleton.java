package ma.example.config;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoURI;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class MongoDBSingleton {

	private static MongoDBSingleton mDbSingleton;
	private static DB db;

	private MongoDBSingleton() {
	};

	public static MongoDBSingleton getInstance() {
		if (mDbSingleton == null) {
			mDbSingleton = new MongoDBSingleton();
		}
		return mDbSingleton;
	}

	public DB getTestdb() {

		//MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
		 MongoURI mongoURI = new
		 MongoURI("mongodb://anil1883:anil1883@ds019846.mlab.com:19846/springtest");

		// get connected
		try {
			db = mongoURI.connectDB();
			MongoCredential credential = MongoCredential.createMongoCRCredential(mongoURI.getUsername(),
					mongoURI.getDatabase(), mongoURI.getPassword());
			MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
			mongoClient.setWriteConcern(WriteConcern.JOURNALED);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}
}
