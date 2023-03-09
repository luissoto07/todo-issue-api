package com.root.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	@NotEmpty(message = "The title must not be empty")
	private String title;

	@Column(name = "description")
	@NotEmpty(message = "The description must not be empty")
	private String description;

	@Column(name = "creation_date")
	@NotEmpty(message = "The creation date must not be empty")
	private String creationDate;

	@Column(name = "completion_date")
	@NotEmpty(message = "The estimated completion date must not be empty")
	private String completionDate;

	@Column(name = "completed")
	@NotEmpty(message = "The completed space must not be empty")
	private String completed;

	@Column(name = "status")
	@NotEmpty(message = "The status space must not be empty")
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

	public Task() {
		super();
	}

	public Task(Long id, @NotEmpty(message = "The title must not be empty") String title,
			@NotEmpty(message = "The description must not be empty") String description,
			@NotEmpty(message = "The creation date must not be empty") String creationDate,
			@NotEmpty(message = "The estimated completion date must not be empty") String completionDate,
			@NotEmpty(message = "The completed space must not be empty") String completed,
			@NotEmpty(message = "The status space must not be empty") String status) {
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
