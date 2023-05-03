package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer updateCustomer(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        if (id > 0) {
            customerRepository.deleteById(id);
        }
    }

    public Customer findCustomerById(Long id) {
        if (id > 0) {
            return customerRepository.findCustomerById(id);
        }
        return null;
    }

    public List<Customer> findCustomersByDoctor_id(Long id) {
        if (id > 0) {
            Doctor doctor = new Doctor();
            doctor.setId(id);
            return customerRepository.findAllByDoctorId(doctor);
        }
        return null;
    }

    public Customer findByEmail(String email) {
        if (email != null) {
            return customerRepository.findCustomerByEmail(email);
        }
        return null;
    }
}
