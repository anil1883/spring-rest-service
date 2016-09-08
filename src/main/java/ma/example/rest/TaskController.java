package ma.example.rest;

import java.util.List;

import javax.annotation.PostConstruct;

import ma.example.domain.task.Task;
import ma.example.domain.task.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	
	private TaskRepository taskRepo;
	
	@Autowired
	public TaskController(TaskRepository taskRepo) {
		super();
		this.setTaskRepo(taskRepo);
	}

	@PostConstruct
	public void initData(){
		taskRepo.deleteAll();
		taskRepo.save(new Task("1","task num 1"));
		taskRepo.save(new Task("2","task num 2"));
		taskRepo.save(new Task("3","task num 3"));
	}
    
	@RequestMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	
	@RequestMapping(value = "/task/{taskId}")
	public Task getTask(@PathVariable("taskId") String taskId) {
	    return taskRepo.findByTaskId(taskId);
	}

	public TaskRepository getTaskRepo() {
		return taskRepo;
	}

	public void setTaskRepo(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}
}
