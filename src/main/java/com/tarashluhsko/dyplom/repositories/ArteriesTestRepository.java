package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArteriesTestRepository extends CrudRepository<ArteriesTest, Long> {
    List<ArteriesTest> findAllByCustomerId(Long customer_id);

}
