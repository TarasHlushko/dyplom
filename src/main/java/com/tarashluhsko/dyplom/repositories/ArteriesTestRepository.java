package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArteriesTestRepository extends JpaRepository<ArteriesTest, Long> {
    List<ArteriesTest> findAllByCustomerId(Long customerId);

    ArteriesTest findFirstByCustomerIdOrderByCreatedDtDesc(Long customerId);

}
