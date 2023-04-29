package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.Comments;
import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Customer customer = new Customer();
        customer.setId(id);
        return commentRepository.findAllByCustomerId(id);
    }
}