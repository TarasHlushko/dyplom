package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.User;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final DoctorRepository userRepository;
    private final CustomerRepository customerRepository;

    public User getUserByLogin(String login) {
        User user = userRepository.findByEmail(login);
        if (user == null) {
            return customerRepository.findCustomerByEmail(login);
        }
        return user;
    }
}
