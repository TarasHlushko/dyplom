package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface CustomerRepository extends CrudRepository<Customer, Long> {
}
