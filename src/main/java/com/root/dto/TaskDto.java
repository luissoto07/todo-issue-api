package com.root.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TaskDto {

	
	private Long id;
	@NotEmpty
	@Size(min=1, message="Title can't be empty")
	private String title;
	@NotEmpty
	@Size(min=5, message="Description must have at leats 5 characters")
	private String description;
	@NotEmpty
	@Size(message="Creation date can't be empty")
	private String creationDate;
	@NotEmpty
	@Size( message="Completion date can't be empty")
	private String completionDate;
	@NotEmpty
	@Size(message="Completed can't be empty")@NotEmpty
	private String completed;
	@NotEmpty
	@Size(message="Status can't be empty")
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TaskDto() {
		super();
	}

	public TaskDto(Long id, String title, String description, String creationDate, String completionDate, String completed,
			String status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.creationDate = creationDate;
		this.completionDate = completionDate;
		this.completed = completed;
		this.status = status;
	}

}
