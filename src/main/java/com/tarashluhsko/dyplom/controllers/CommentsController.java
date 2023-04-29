package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Comments;
import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Comments comment) {
        ResponseEntity response = null;
        try {
            comment.setDate(LocalDateTime.now());
            Comments savedComment = commentService.create(comment);
            if (savedComment.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given comment is successfully created");
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Comments comment) {
        ResponseEntity<String> response = null;
        try {
            commentService.updateComment(comment);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given comment details are successfully updated");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam Long id) {
        ResponseEntity<String> response = null;
        try {
            commentService.deleteComment(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given commment is successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }


    @GetMapping("/getComments")
    public List<Comments> findCommentsByCustomerId(@RequestParam Long id) {
        return commentService.getComments(id);
    }
}
