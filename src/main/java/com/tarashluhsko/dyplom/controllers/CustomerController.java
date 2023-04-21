package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
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
                    .body("Given user details are successfully registered");
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
                    .body("Given user details are successfully registered");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }
        return response;
    }


}
