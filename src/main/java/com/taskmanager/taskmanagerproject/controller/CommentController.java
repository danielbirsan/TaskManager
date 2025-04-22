package com.taskmanager.taskmanagerproject.controller;

import com.taskmanager.taskmanagerproject.model.Comment;
import com.taskmanager.taskmanagerproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    // create comment
    public ResponseEntity<Comment> create(@RequestBody Comment c) {
        return new ResponseEntity<>(commentService.create(c), HttpStatus.CREATED);
    }

    // get all comments
    @GetMapping
    public List<Comment> all() {
        return commentService.findAll();
    }

    // get comment by id
    @GetMapping("/{id}")
    public ResponseEntity<Comment> byId(@PathVariable Long id) {
        return commentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //delete comment by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}