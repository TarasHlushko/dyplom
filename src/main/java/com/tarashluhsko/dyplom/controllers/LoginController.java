package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.services.CustomerService;
import com.tarashluhsko.dyplom.services.DoctorService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final CustomerService customerService;

    private final DoctorService doctorService;

    public LoginController(CustomerService customerService, DoctorService doctorService) {
        this.customerService = customerService;
        this.doctorService = doctorService;
    }

    @RequestMapping("/customer")
    public Customer getCustomerDetailsAfterLogin(Authentication authentication) {
        Customer customer = customerService.findByEmail(authentication.getName());
        if (customer != null) {
            return customer;
        } else {
            return null;
        }

    }

    @RequestMapping("/doctor")
    public Doctor getDoctorDetailsAfterLogin(Authentication authentication) {
        Doctor doctor = doctorService.findByEmail(authentication.getName());
        if (doctor != null) {
            return doctor;
        }
        return null;
    }
}
