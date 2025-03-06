package com.example.TaskManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagementSystem.dto.TaskDTO;
import com.example.TaskManagementSystem.entity.Task;
import com.example.TaskManagementSystem.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask(TaskDTO taskDTO) {
		Task task = new Task();
		task.setTitle(taskDTO.getTile());
		task.setDescription(taskDTO.getDescription());
		task.setDueData(taskDTO.getDueData());
		return taskRepository.save(task);
	}
	
	public  List<Task> getAllTask() {
		return taskRepository.findAll();
	}
	
	public Task updateTask(Long taskId, TaskDTO taskDTO) {
		Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
		if (taskDTO.getTile() != null) {
		task.setTitle(taskDTO.getTile());
		}
		if (taskDTO.getDescription() != null) {
		task.setDescription(taskDTO.getDescription());
		}
		
		if (taskDTO.getDueData() != null) {
		task.setDueData(taskDTO.getDueData());
		}
		return taskRepository.save(task);
	}

	public boolean deleteTask(Long taskId) {
        // Check if the task exists
		if (taskRepository.existsById(taskId)) {
	        taskRepository.deleteById(taskId); // Delete the task
	        return true;  // Task was deleted
	    }
        return false; // Task not found
    }

	public boolean deleteAllTask() {
		 taskRepository.deleteAll();
		return true;
	}
}
