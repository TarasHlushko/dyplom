package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyDoctorDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    public MyDoctorDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor == null) {
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                email,
                doctor.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(doctor.getRole())));
    }
}
