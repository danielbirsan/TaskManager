package com.taskmanager.taskmanagerproject.service;

import com.taskmanager.taskmanagerproject.model.Comment;
import com.taskmanager.taskmanagerproject.model.Task;
import com.taskmanager.taskmanagerproject.repository.CommentRepository;
import com.taskmanager.taskmanagerproject.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    //New task
    @Transactional
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    //Add subtask
    @Transactional
    public Optional<Task> addSubtask(Long parentId, Task subtask) {
        return taskRepository.findById(parentId)
                .map(parent -> {
                    subtask.setResponsible(null);
                    subtask.setParent(parent);
                    return taskRepository.save(subtask);
                });
    }

    //Update task
    @Transactional
    public Optional<Task> update(Long id, Task source) {
        return taskRepository.findById(id)
                .map(t -> {
                    t.setTitle(source.getTitle());
                    t.setDescription(source.getDescription());
                    t.setDueDate(source.getDueDate());
                    t.setResponsible(source.getResponsible());
                    return t;
                });
    }

    // Delete a task and its graph (sub‑tasks + comments).
    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    // ------------------- read‑only queries -------------------

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findByResponsible(String responsible) {
        return taskRepository.findByResponsible(responsible);
    }

    public List<Task> searchByTitle(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // Shortcut to add a comment via TaskService.
    @Transactional
    public Optional<Comment> addComment(Long taskId, Comment comment) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    comment.setTask(task);
                    return commentRepository.save(comment);
                });
    }
}