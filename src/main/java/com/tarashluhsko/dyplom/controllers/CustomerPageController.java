package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.*;
import com.tarashluhsko.dyplom.services.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerPageController {
    private final CustomerService customerService;
    private final BiochemicalTestService biochemicalTestService;
    private final ArteriesTestService arteriesTestService;
    private final MicrovesselsTestService microvesselsTestService;

    private final CommentService commentService;

    public CustomerPageController(CustomerService customerService, BiochemicalTestService biochemicalTestService, ArteriesTestService arteriesTestService, MicrovesselsTestService microvesselsTestService, CommentService commentService) {
        this.customerService = customerService;
        this.biochemicalTestService = biochemicalTestService;
        this.arteriesTestService = arteriesTestService;
        this.microvesselsTestService = microvesselsTestService;
        this.commentService = commentService;
    }

    @GetMapping("/getCustomerPage/{id}")
    public CustomerPage getCustomerPageInfo(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        List<BiochemicalTest> biochemicalTest = biochemicalTestService.getBiochemicalTests(id);
        List<ArteriesTest> arteriesTests = arteriesTestService.getTests(id);
        List<MicrovesselsTest> microvesselsTests = microvesselsTestService.getTests(id);
        List<Comments> comments = commentService.getComments(id);
        return new CustomerPage(customer.getDoctorId(), customer, biochemicalTest, arteriesTests, microvesselsTests, comments);
    }
}
