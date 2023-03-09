package com.root.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.root.dto.TaskDto;
import com.root.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<TaskDto> saveTask(@Valid @RequestBody TaskDto taskDto) {
		return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.OK);
	}

	@GetMapping("/getall")
	public List<TaskDto> allTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<TaskDto> getTaskById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(taskService.findTaskById(id));
	}

	@GetMapping("findbystatus/{completed}")
	public List<TaskDto> getTasksByStatus(@PathVariable(name = "completed") String completed) {
		return taskService.findTaskByStatus(completed);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("updatetask/{id}")
	public ResponseEntity<TaskDto> updateTask(@Valid @RequestBody TaskDto taskDto, @PathVariable(name = "id") Long id) {
		TaskDto taskResponse = taskService.updateTask(taskDto, id);
		return new ResponseEntity<>(taskResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("markasdone/{id}")
	public ResponseEntity<TaskDto> markAsDone(@Valid @RequestBody TaskDto taskDto, @PathVariable(name = "id") Long id) {
		TaskDto taskResponse = taskService.updateTaskAsDone(taskDto, id);
		return new ResponseEntity<>(taskResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable(name = "id") Long id) {
		taskService.deleteTask(id);
		return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
	}

}
