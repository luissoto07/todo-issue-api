package com.root.dto;

import java.util.List;

//1:24:12
public class TaskResponse {

	//To list tasks
	private List<TaskDto> content;

	public List<TaskDto> getContent() {
		return content;
	}

	public void setContent(List<TaskDto> content) {
		this.content = content;
	}

	public TaskResponse() {
		super();
	}
	
}
