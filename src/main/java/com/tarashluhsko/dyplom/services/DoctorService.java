package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id.intValue());
    }

    public void update(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findById(Long id) {
        return doctorRepository.findDoctorById(id);
    }
}
