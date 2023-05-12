package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Transactional
public interface DoctorRepository extends UserRepository<Doctor> {

    Doctor findFirstById(Long id);

//    Doctor findByEmail(String email);

    List<Doctor> findAllBySecondNameStartsWithOrFirstNameStartsWith(String value, String value2);

//    void update(Doctor Doctor);
}
