package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(Doctor doctor) {
        if (doctor != null) {
            return doctorRepository.save(doctor);
        }
        return null;
    }

    public void delete(Long id) {
        if (id > 0) {
            doctorRepository.deleteById(id);
        }
    }

    public void update(Doctor doctor) {
        if (doctor != null) {
            doctorRepository.save(doctor);
        }
    }

    public Doctor findById(Long id) {
        if (id > 0) {
            return doctorRepository.findFirstById(id);
        }
        return null;
    }

    public Doctor findByEmail(String email) {
        if (email != null) {
            return doctorRepository.findByEmail(email);
        }
        return null;
    }

    public List<Doctor> doctorList(String value) {
        value = value.toLowerCase();
        return doctorRepository.findAllBySecondNameStartsWithOrFirstNameStartsWith(value, value);
    }

}
