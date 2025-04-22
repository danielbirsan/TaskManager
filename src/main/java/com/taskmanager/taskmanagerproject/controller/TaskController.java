package com.taskmanager.taskmanagerproject.controller;

import com.taskmanager.taskmanagerproject.model.Comment;
import com.taskmanager.taskmanagerproject.model.Task;
import com.taskmanager.taskmanagerproject.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // ---------- CRUD ----------

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        return taskService.update(id, task)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    // ---------- relations ----------

    @PostMapping("/{id}/subtasks")
    public ResponseEntity<Task> addSubtask(@PathVariable Long id, @RequestBody Task subtask) {
        return taskService.addSubtask(id, subtask)
                .map(t -> new ResponseEntity<>(t, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return taskService.addComment(id, comment)
                .map(c -> new ResponseEntity<>(c, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ---------- queries ----------

    @GetMapping
    public List<Task> all() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> byId(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/responsible")
    public List<Task> byResponsible(@RequestParam String name) {
        return taskService.findByResponsible(name);
    }

    @GetMapping("/search")
    public List<Task> search(@RequestParam String title) {
        return taskService.searchByTitle(title);
    }
}