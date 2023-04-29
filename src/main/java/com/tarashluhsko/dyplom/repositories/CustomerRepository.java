package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.OptionalLong;

@Repository
public
interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerById(Long id);

//    @Query("select * from customers where doctor_id = :#{id}")
//    List<Customer> findAllByDoctorId(Long id);

    @Query("select c from Customer c where c.doctorId = ?1")
    List<Customer> findAllByDoctorId(Doctor doctor);
}
