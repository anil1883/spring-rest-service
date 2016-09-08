package ma.example.domain.task;

import ma.example.domain.BaseEntity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(collection = "Tasks")
public class Task extends BaseEntity{
	
    	@Indexed(unique=true)
    	private String taskId;
    
	private String name;
	
	public Task(String taskId, String name) {
		super();
		this.taskId = taskId;
		this.name = name;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", name=" + name + "]";
	}
}
