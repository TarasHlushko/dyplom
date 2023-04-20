package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    void deleteById(int id);

    Doctor findDoctorById(Long id);

//    void update(Doctor Doctor);
}
