package ma.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@EnableMongoRepositories(basePackages="ma.example.domain.task")
public class MongoConfig {
    
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "taskdb");
	}
	public @Bean MongoTemplate mongoTemplate() throws Exception {
	      return new MongoTemplate(mongoDbFactory());
	}
}
