package com.frondara.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frondara.entity.Task;
import com.frondara.repositories.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

	private TaskRepository taskRepository;

	@Transactional(readOnly = true)
	public List<Task> getTasks(){
		return taskRepository.findAll();
	}
}
