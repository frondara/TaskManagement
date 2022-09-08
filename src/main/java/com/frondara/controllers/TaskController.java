package com.frondara.controllers;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frondara.dto.CountType;
import com.frondara.entity.Task;
import com.frondara.services.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;
	
	
	@GetMapping("/tasks/vData/percentCountType")
	public List<CountType> getPercentageGroupByType(){
		return taskService.getPercentageGroupByType();
		
	}
	
	@GetMapping("/tasks")
	public List<Task> getTasks(){
		return taskService.getTasks();
		
	}
	
	@PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.save(task);
		
	}
	
	@GetMapping("/task/{id}")
	public Task getById(@PathVariable Long id) {
		return taskService.getTasksById(id).orElseThrow(() -> new EntityNotFoundException("Request Not Found"));
		
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task taskParam, @PathVariable Long id) {
		if(taskService.existById(id)) {
			Task task = taskService.getTasksById(id).orElseThrow(() -> new EntityNotFoundException("Request Not Found"));
			task.setTitle(taskParam.getTitle());
			task.setDueDate(taskParam.getDueDate());
			task.setType(taskParam.getType());
			task.setDescription(taskParam.getDescription());
			taskService.save(task);
			
			return ResponseEntity.ok().body(task);
			
		}else {
			HashMap<String, String> message = new HashMap();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		HashMap<String, String> message = new HashMap();
		if(taskService.existById(id)) {
			Task task = taskService.getTasksById(id).orElseThrow(() -> new EntityNotFoundException("Request Not Found"));
			taskService.delete(id);
			message.put("message", id + " task removed");
			return ResponseEntity.status(HttpStatus.OK).body(message);
			
		}else {
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
	}
}
