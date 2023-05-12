package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.model.User;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import com.tarashluhsko.dyplom.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsSevice implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final DoctorRepository doctorRepository;

    public MyUserDetailsSevice(CustomerRepository customerRepository, DoctorRepository doctorRepository) {
        this.customerRepository = customerRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email);

        if(customer == null) {
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                email,
                customer.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(customer.getRole())));
    }
}
