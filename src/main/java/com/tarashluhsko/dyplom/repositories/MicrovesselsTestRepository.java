package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.ArteriesTest;
import com.tarashluhsko.dyplom.model.MicrovesselsTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MicrovesselsTestRepository extends CrudRepository<MicrovesselsTest, Long> {
    List<MicrovesselsTest> findAllByCustomerId(Long customer_id);

    MicrovesselsTest findFirstByCustomerIdOrderByCreatedDesc(Long customer_id);
}
