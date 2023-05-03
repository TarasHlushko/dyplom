package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findFirstById(Long id);

    Doctor findByEmail(String email);

//    void update(Doctor Doctor);
}
