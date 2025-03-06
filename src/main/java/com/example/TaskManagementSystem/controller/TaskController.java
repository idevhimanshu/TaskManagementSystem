package com.example.TaskManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManagementSystem.dto.TaskDTO;
import com.example.TaskManagementSystem.entity.Task;
import com.example.TaskManagementSystem.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
	    List<Task> tasks = taskService.getAllTask();
	    return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskService.updateTask(id, taskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {
	    boolean isDeleted = taskService.deleteTask(id);

	    if (isDeleted) {
	        return ResponseEntity.ok("Task successfully deleted"); // 200 OK with message
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found"); // 404 Not Found
	    }
	}

	
	@DeleteMapping
    public ResponseEntity<String> deleteTask() {
        boolean isDeleted = taskService.deleteAllTask();
        
        if (isDeleted) {
            return new ResponseEntity<>("Task successfully deleted", HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND); // 404
        }
    }

}
