package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.*;
import com.tarashluhsko.dyplom.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private final CommentRepository commentRepository;



    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;

    }

    public Comments create(Comments comments) {
        return commentRepository.save(comments);
    }

    public void updateComment(Comments comments) {
        if (comments != null) {
            commentRepository.save(comments);
        }
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comments> getComments(Long id) {
        return commentRepository.findAllByCustomerId(id);
    }


}
