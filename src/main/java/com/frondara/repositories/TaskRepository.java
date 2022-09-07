package com.frondara.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frondara.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
