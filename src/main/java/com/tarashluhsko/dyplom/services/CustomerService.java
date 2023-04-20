package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer update(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }
}
