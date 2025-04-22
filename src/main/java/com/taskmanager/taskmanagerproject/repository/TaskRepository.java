package com.taskmanager.taskmanagerproject.repository;

import com.taskmanager.taskmanagerproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query: find all tasks by responsible name
    List<Task> findByResponsible(String responsible);

    List<Task> findByTitleContainingIgnoreCase(String title);
}