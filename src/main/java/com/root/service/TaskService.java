package com.root.service;

import java.util.List;

import com.root.dto.TaskDto;

public interface TaskService {

	public TaskDto createTask(TaskDto taskDto);

	public List<TaskDto> getAllTasks();

	public TaskDto findTaskById(long id);

	public List<TaskDto> findTaskByStatus(String completed);

	public TaskDto updateTask(TaskDto taskDto, long id);

	public TaskDto updateTaskAsDone(TaskDto taskDto, long id);

	public void deleteTask(long id);

}
