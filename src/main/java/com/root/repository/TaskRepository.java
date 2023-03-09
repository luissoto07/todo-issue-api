package com.root.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
