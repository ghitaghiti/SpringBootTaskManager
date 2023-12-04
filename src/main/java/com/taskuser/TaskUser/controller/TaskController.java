package com.taskuser.TaskUser.controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskuser.TaskUser.model.Task;
import com.taskuser.TaskUser.repository.TaskRepository;
/* 
 *****************************
 *   Questions from 1 to 4   *
 *****************************
@RestController
@RequestMapping("/tasks")

public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
 	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		 return taskRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskRepository.save(task));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
		return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(task.getTitle());
                    existingTask.setDescription(task.getDescription());
                    return ResponseEntity.ok(taskRepository.save(existingTask));
                })
                .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(Long id) {
		 return taskRepository.findById(id).map(task -> {
            	taskRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
	            }).orElse(ResponseEntity.notFound().build());
}
}*/

/* 
 ******************************
 *  Questions from 5 to end   *
 ******************************
*/

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        taskRepository.findById(id).ifPresent(task -> model.addAttribute("task", task));
        return "taskDetails";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskForm";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        taskRepository.findById(id).ifPresent(task -> model.addAttribute("task", task));
        return "taskForm";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }
}

