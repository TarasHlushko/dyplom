package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.OptionalLong;

@Transactional
public interface CustomerRepository extends UserRepository<Customer> {
    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

//    @Query("select * from customers where doctor_id = :#{id}")
//    List<Customer> findAllByDoctorId(Long id);

    @Query("select c from Customer c where c.doctorId = ?1")
    List<Customer> findAllByDoctorId(Doctor doctor);

//    @Query("SELECT u FROM Customer u WHERE u.birth_dt BETWEEN :startDate AND :endDate")
//    List<Customer> findAllByAge(LocalDate startDate, LocalDate endDate);
    @Query(value = "from Customer t where t.birthDt BETWEEN :startDate AND :endDate AND t.doctorId = :doctor")
    List<Customer> findAllByBirthDtBetween(@Param("endDate")LocalDate birthDt,@Param("startDate") LocalDate birthDt2,
                                           Doctor doctor);
    List<Customer> findAllByDoctorIdAndBirthDtBetween(Doctor doctorId, LocalDate birthDt, LocalDate birthDt2);

    List<Customer> findAllByDoctorIdFirstNameStartsWithOrLastNameStartsWith(String value1, String value2);
    List<Customer> findAllByDoctorIdAndFirstNameStartsWithOrLastNameStartsWith(Doctor doctorId, String firstName, String lastName);
}
