package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.*;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import com.tarashluhsko.dyplom.services.ArteriesTestService;
import com.tarashluhsko.dyplom.services.BiochemicalTestService;
import com.tarashluhsko.dyplom.services.CustomerService;
import com.tarashluhsko.dyplom.services.MicrovesselsTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    private final BiochemicalTestService biochemicalTestService;
    private final ArteriesTestService arteriesTestService;
    private final MicrovesselsTestService microvesselsTestService;

    private final PasswordEncoder passwordEncoder;


    public CustomerController(CustomerService customerService, BiochemicalTestService biochemicalTestService, ArteriesTestService arteriesTestService, MicrovesselsTestService microvesselsTestService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.biochemicalTestService = biochemicalTestService;
        this.arteriesTestService = arteriesTestService;
        this.microvesselsTestService = microvesselsTestService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;
        try {
            System.out.println(customer.getPassword() + " " + customer.getEmail());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String hashPwd = passwordEncoder.encode(customer.getPassword());
//            customer.setBirth_dt(LocalDateTime.now());
            customer.setPassword(hashPwd);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
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

    @GetMapping("/getAccount/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/getPatients/{id}")
    public List<PatientsWithTest> findCustomerByDoctor_id(@PathVariable Long id) {
        List<Customer> customersList =  customerService.findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        for (Customer customer : customersList) {
            List<ArteriesTest> arteriesTests = customer.getArteriesTests();
            List<BiochemicalTest> biochemicalTests = customer.getBiochemicalTests();
            List<MicrovesselsTest> microvesselsTests = customer.getMicrovesselsTests();
            patientsWithTests.add(new PatientsWithTest(customer,biochemicalTests,arteriesTests,microvesselsTests));
        }
        return patientsWithTests;
    }

    @GetMapping("/getSearchedPatients/{id}")
    public List<PatientsWithTest> findSearchedCustomerByDoctorId(@PathVariable Long id, @RequestParam String name) {
//        String lowerCasedValues = name.toLowerCase();
        System.out.println("Hello " + name);
        List<Customer> customersList = customerService.findCustomersByFirstLetter(id,name);
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        for (Customer customer : customersList) {
            List<ArteriesTest> arteriesTests = customer.getArteriesTests();
            List<BiochemicalTest> biochemicalTests = customer.getBiochemicalTests();
            List<MicrovesselsTest> microvesselsTests = customer.getMicrovesselsTests();
            patientsWithTests.add(new PatientsWithTest(customer,biochemicalTests,arteriesTests,microvesselsTests));
        }
        return patientsWithTests;

    }

    @GetMapping("/getPatients/biochemical/{id}")
    public List<PatientsWithTest> sortCustomerByBiochemicalTest(@PathVariable Long id) {
        List<Customer> customersList =  customerService.findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = customerService.patientsWithBiochemicalTests(id);
        return patientsWithTests;
    }
    @GetMapping("/getPatients/arteries/{id}")
    public List<PatientsWithTest> sortCustomerByArteriesTest(@PathVariable Long id) {
        List<Customer> customersList =  customerService.findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = customerService.patientsWithArteriesTests(id);
        return patientsWithTests;
    }

    @GetMapping("/getPatients/microvessels/{id}")
    public List<PatientsWithTest> sortCustomerByMicrovesselTest(@PathVariable Long id) {
        List<Customer> customersList =  customerService.findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = customerService.patientsWithMicrovesselTests(id);
        return patientsWithTests;
    }

    @GetMapping("/test/{id}")
    public List<Customer> sortCustomerByTestAndAge( @PathVariable Long id) {
        return customerService.sortPatientsByAge(id, 0);
    }

    @GetMapping("/getPatients/sorted/{id}")
    public List<PatientsWithTest> sorted(@RequestBody List<String> inputData,
                                         @PathVariable Long id) {
        System.out.println(inputData.get(0) + "  " + inputData.get(1));

        return customerService.patientsForDoctorPage(id, inputData.get(1), inputData.get(0));
    }

}
