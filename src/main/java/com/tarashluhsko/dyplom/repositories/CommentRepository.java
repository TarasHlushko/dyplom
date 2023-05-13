package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByCustomerId(Long customerId);

}
