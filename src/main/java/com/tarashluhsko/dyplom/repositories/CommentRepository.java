package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Comments;
import com.tarashluhsko.dyplom.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comments, Long> {
    List<Comments> findAllByCustomerId(Long customer_id);

}
