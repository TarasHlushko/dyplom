package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

//    private final PasswordEncoder passwordEncoder;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
//        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
            System.out.println(customer.getPassword() + " " + customer.getEmail());
//            String hashPwd = passwordEncoder.encode(customer.getPassword());
//            customer.setPassword(hashPwd);
            Customer savedCustomer = customerService.createCustomer(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
            customerService.updateCustomer(customer);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given user details are successfully updated");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam Long id) {
        ResponseEntity<String> response = null;
        try {
            customerService.deleteCustomer(id);
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Given user details are successfully deleted");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/getAccount")
    public Customer findCustomerById(@RequestParam Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/getPatients")
    public List<Customer> findCustomerByDoctor_id(@RequestParam Long id) {
        return customerService.findCustomersByDoctor_id(id);
    }
}
