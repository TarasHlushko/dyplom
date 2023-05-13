package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends UserRepository<Customer> {
    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    @Query("select c from Customer c where c.doctorId = ?1")
    List<Customer> findAllByDoctorId(Long doctorId);
    @Query(value = "from Customer t where t.birthDt BETWEEN :startDate AND :endDate AND t.doctorId = :doctorId")
    List<Customer> findAllByBirthDtBetween(@Param("endDate") LocalDate birthDt,
                                           @Param("startDate") LocalDate birthDt2,
                                           @Param("doctorId") Long doctor);
    List<Customer> findAllByDoctorIdAndFirstNameStartsWithOrLastNameStartsWith(Long doctorId, String firstName, String lastName);
}
