package ma.example.domain.task;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>{
	
	Task findByTaskId(String taskId);
	
}
