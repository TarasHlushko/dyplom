package com.tarashluhsko.dyplom.repositories;

import com.tarashluhsko.dyplom.model.Doctor;

import java.util.List;

public interface DoctorRepository extends UserRepository<Doctor> {
    Doctor findFirstById(Long id);

    List<Doctor> findAllBySecondNameStartsWithOrFirstNameStartsWith(String value, String value2);
}
