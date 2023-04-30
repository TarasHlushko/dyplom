package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.BiochemicalTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiochemicalTestRepository extends CrudRepository<BiochemicalTest, Long> {
    List<BiochemicalTest> findAllByCustomerId(Long customer_id);
}
