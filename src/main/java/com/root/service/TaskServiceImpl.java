package com.root.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.dto.TaskDto;
import com.root.entity.Task;
import com.root.exceptions.ResourceNotFoundException;
import com.root.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public TaskDto createTask(TaskDto taskDto) {
//		// Change DTO -> Entity
//		Task task = new Task();
//		task.setTitle(taskDto.getTitle());
//		task.setDescription(taskDto.getDescription());
//		task.setCreationDate(taskDto.getCreationDate());
//		task.setCompletionDate(taskDto.getCompletionDate());
//		task.setCompleted(taskDto.getCompleted());
//		task.setStatus(taskDto.getStatus());
//
//		Task newTask = taskRepository.save(task);
//
//		// Change entity -> DTO
//		TaskDto taskResponse = new TaskDto();
//		taskResponse.setId(newTask.getId());
//		taskResponse.setTitle(newTask.getTitle());
//		taskResponse.setDescription(newTask.getDescription());
//		taskResponse.setCreationDate(newTask.getCreationDate());
//		taskResponse.setCompletionDate(newTask.getCompletionDate());
//		taskResponse.setCompleted(newTask.getCompleted());
//		taskResponse.setStatus(newTask.getStatus());
//
//		return taskResponse;

		Task task = mapEntity(taskDto);
		Task newTask = taskRepository.save(task);

		TaskDto taskResponse = mapDto(newTask);

		return taskResponse;
	}

	@Override
	public List<TaskDto> getAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return tasks.stream().map(task -> mapDto(task)).collect(Collectors.toList());
	}

	private TaskDto mapDto(Task task) {
//		TaskDto taskDto = new TaskDto();
//		taskDto.setId(task.getId());
//		taskDto.setTitle(task.getTitle());
//		taskDto.setDescription(task.getDescription());
//		taskDto.setCreationDate(task.getCreationDate());
//		taskDto.setCompletionDate(task.getCompletionDate());
//		taskDto.setCompleted(task.getCompleted());
//		taskDto.setStatus(task.getStatus());
		
		TaskDto taskDto = modelMapper.map(task, TaskDto.class);

		return taskDto;
	}

	private Task mapEntity(TaskDto taskDto) {
//		Task task = new Task();
//		task.setTitle(taskDto.getTitle());
//		task.setDescription(taskDto.getDescription());
//		task.setCreationDate(taskDto.getCreationDate());
//		task.setCompletionDate(taskDto.getCompletionDate());
//		task.setCompleted(taskDto.getCompleted());
//		task.setStatus(taskDto.getStatus());
		
		Task task = modelMapper.map(taskDto, Task.class);

		return task;
	}

	@Override
	public TaskDto findTaskById(long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

		return mapDto(task);
	}

	@Override
	public List<TaskDto> findTaskByStatus(String completed) {
		List<Task> allTasks = taskRepository.findAll();
		List<TaskDto> list = allTasks.stream().filter(task -> task.getCompleted().equals(completed))
				.map(task -> mapDto(task)).collect(Collectors.toList());
		return list;
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto, long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setCreationDate(taskDto.getCreationDate());
		task.setCompletionDate(taskDto.getCompletionDate());
		task.setCompleted(taskDto.getCompleted());
		task.setStatus(taskDto.getStatus());

		Task updatedTask = taskRepository.save(task);

		return mapDto(updatedTask);
	}

	@Override
	public TaskDto updateTaskAsDone(TaskDto taskDto, long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
		task.setCompleted(taskDto.getCompleted());
		task.setStatus(taskDto.getStatus());

		Task updatedTask = taskRepository.save(task);

		return mapDto(updatedTask);
	}

	@Override
	public void deleteTask(long id) {
		Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
		taskRepository.delete(task);
	}

}
