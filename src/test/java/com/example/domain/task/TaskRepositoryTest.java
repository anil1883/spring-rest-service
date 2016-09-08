package com.example.domain.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ma.example.domain.task.Task;
import ma.example.domain.task.TaskRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.SpringRestAppTests;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringRestAppTests.class)
public class TaskRepositoryTest {
	
    @Autowired
    TaskRepository taskRepository;

    @Before()
    public void setUp() {

    }

    @After
    public void shutdown() {
        this.taskRepository.deleteAll();
    }
    
    @Test
    public void testTaskCreate() {
    	
    	 // create task
        Task task = new Task("123","task num 123");
        taskRepository.save(task);
        
        String id = task.getId();
        assertTrue(taskRepository.exists(id));
    }
    
    @Test
    public void testFindById() {
    	
        Task task = new Task("32","task num 32");
        taskRepository.save(task);

        Task taskr = taskRepository.findByTaskId("32");
        assertEquals(taskr.getName(), "task num 32");
    }
}
