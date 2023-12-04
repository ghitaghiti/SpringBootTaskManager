package com.taskuser.TaskUser.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taskuser.TaskUser.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
