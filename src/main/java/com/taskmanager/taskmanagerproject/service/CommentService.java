package com.taskmanager.taskmanagerproject.service;

import com.taskmanager.taskmanagerproject.model.Comment;
import com.taskmanager.taskmanagerproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment create(Comment c) {
        return commentRepository.save(c);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
