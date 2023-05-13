package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.BiochemicalTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiochemicalTestRepository extends JpaRepository<BiochemicalTest, Long> {
    List<BiochemicalTest> findAllByCustomerId(Long customerId);

    BiochemicalTest findFirstByCustomerIdOrderByCreatedDesc(Long customerId);
}
