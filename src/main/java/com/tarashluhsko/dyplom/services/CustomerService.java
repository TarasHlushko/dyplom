package com.tarashluhsko.dyplom.services;

import com.tarashluhsko.dyplom.model.*;
import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private ApplicationContext context;
    private final CustomerRepository customerRepository;
    private final BiochemicalTestService biochemicalTestService;
    private final ArteriesTestService arteriesTestService;
    private final MicrovesselsTestService microvesselsTestService;

    private Map<String, Class<?>> tests;
    private Map<String, Integer> ageGroup;

    public CustomerService(CustomerRepository customerRepository, BiochemicalTestService biochemicalTestService, ArteriesTestService arteriesTestService, MicrovesselsTestService microvesselsTestService) {
        this.customerRepository = customerRepository;
        this.biochemicalTestService = biochemicalTestService;
        this.arteriesTestService = arteriesTestService;
        this.microvesselsTestService = microvesselsTestService;

        tests = new HashMap<>();
        tests.put("biochemical", BiochemicalTest.class);
        tests.put("arteries", ArteriesTest.class);
        tests.put("microvessel", MicrovesselsTest.class);

        this.ageGroup = new HashMap<>();
        ageGroup.put("1", 0);
        ageGroup.put("2", 20);
        ageGroup.put("3", 40);
        ageGroup.put("4", 60);
        ageGroup.put("5", 80);
    }


    public Customer createCustomer(Customer customer) {
        if (customer != null) {
            customer.setRole("ROLE_CUSTOMER");
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


    public List<Customer> findCustomersByFirstLetter(Long id, String value) {
        if (!value.isEmpty()) {
            Doctor doctor = new Doctor();
            doctor.setId(id);
            return customerRepository.findAllByDoctorIdAndFirstNameStartsWithOrLastNameStartsWith(doctor, value, value);
        }
        return null;
    }

    public List<PatientsWithTest> patientsWithBiochemicalTests(Long id) {
        List<Customer> customers = findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getBiochemicalTests() != null && customer.getBiochemicalTests().size() > 0) {
                patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                        customer.getArteriesTests(), customer.getMicrovesselsTests()));
            }
        }
        return patientsWithTests;
    }

    public List<PatientsWithTest> patientsWithArteriesTests(Long id) {
        List<Customer> customers = findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getBiochemicalTests() != null && customer.getArteriesTests().size() > 0) {
                patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                        customer.getArteriesTests(), customer.getMicrovesselsTests()));
            }
        }
        return patientsWithTests;
    }

    public List<PatientsWithTest> patientsWithMicrovesselTests(Long id) {
        List<Customer> customers = findCustomersByDoctor_id(id);
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getBiochemicalTests() != null && customer.getMicrovesselsTests().size() > 0) {
                patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                        customer.getArteriesTests(), customer.getMicrovesselsTests()));
            }
        }
        return patientsWithTests;
    }

    public List<Customer> sortPatientsByAge(Long id, int age) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        List<Customer> customers = customerRepository.findAllByBirthDtBetween(LocalDate.now().minusYears(age), LocalDate.now().minusYears(age + 20),
                doctor);
        System.out.println(customers.size());
        System.out.println(LocalDate.now().minusYears(age + 20));
        System.out.println(LocalDate.now().minusYears(age));
        return customers;
    }


    public List<PatientsWithTest> patientsForDoctorPage(Long id, String test, String age) {
        List<PatientsWithTest> patientsWithTests = new ArrayList<>();
        List<Customer> customers;
        if (age != null && !age.isEmpty()) {
            customers = sortPatientsByAge(id, ageGroup.get(age));
            if (test != null && !test.isEmpty()) {
                if (test.equals("biochemical")) {
                    for (Customer customer : customers) {
                        if (customer.getBiochemicalTests() != null && customer.getBiochemicalTests().size() > 0) {
                            patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                                    customer.getArteriesTests(), customer.getMicrovesselsTests()));
                        }
                    }
                    return patientsWithTests;
                }
            } else if (test.equals("arteries")) {
                for (Customer customer : customers) {
                    if (customer.getArteriesTests() != null && customer.getArteriesTests().size() > 0) {
                        patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                                customer.getArteriesTests(), customer.getMicrovesselsTests()));
                    }
                }
                return patientsWithTests;
            } else {
                for (Customer customer : customers) {
                    if (customer.getMicrovesselsTests() != null && customer.getMicrovesselsTests().size() > 0) {
                        patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                                customer.getArteriesTests(), customer.getMicrovesselsTests()));
                    }
                }
                return patientsWithTests;
            }
            for (Customer customer : customers) {

                patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                        customer.getArteriesTests(), customer.getMicrovesselsTests()));

            }
            return patientsWithTests;
        } else {
            customers = findCustomersByDoctor_id(id);
            if (test != null && !test.isEmpty()) {
                if (test.equals("biochemical")) {
                    for (Customer customer : customers) {
                        if (customer.getBiochemicalTests() != null && customer.getBiochemicalTests().size() > 0) {
                            patientsWithTests.add(new PatientsWithTest(customer, Collections.singletonList(biochemicalTestService.getLatestTest(customer.getId())),
                                    Collections.singletonList(arteriesTestService.getLatestTest(customer.getId())),
                                    Collections.singletonList(microvesselsTestService.getLatestTest(customer.getId()))));
                        }
                    }
                    return patientsWithTests;
                }
            } else if (test.equals("arteries")) {
                for (Customer customer : customers) {
                    if (customer.getArteriesTests() != null && customer.getArteriesTests().size() > 0) {
                        patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                                customer.getArteriesTests(), customer.getMicrovesselsTests()));
                    }
                }
                return patientsWithTests;
            } else {
                for (Customer customer : customers) {
                    if (customer.getMicrovesselsTests() != null && customer.getMicrovesselsTests().size() > 0) {
                        patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                                customer.getArteriesTests(), customer.getMicrovesselsTests()));
                    }
                }
                return patientsWithTests;
            }

        }
        for (Customer customer : customers) {
            patientsWithTests.add(new PatientsWithTest(customer, customer.getBiochemicalTests(),
                    customer.getArteriesTests(), customer.getMicrovesselsTests()));

        }
        return patientsWithTests;

    }
}
//        ageGroup.put("1", 0);
//        ageGroup.put("2", 20);
//        ageGroup.put("3", 40);
//        ageGroup.put("4", 60);
//        ageGroup.put("5", 80);
//        tests.put("biochemical", BiochemicalTest.class);
//        tests.put("arteries", ArteriesTest.class);
//        tests.put("microvessel", MicrovesselsTest.class);