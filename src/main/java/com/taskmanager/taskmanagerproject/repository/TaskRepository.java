package com.taskmanager.taskmanagerproject.repository;

import com.taskmanager.taskmanagerproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}