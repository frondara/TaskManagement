package com.frondara.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frondara.entity.Task;
import com.frondara.services.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
@AllArgsConstructor
public class TaskController {

	private TaskService taskService;
	
	@GetMapping("/tasks")
	public List<Task> getTasks(){
		return taskService.getTasks();
		
	}
}
