package com.tarashluhsko.dyplom.model;

import java.util.List;

public class CustomerPage {
    private Doctor doctor;

    private Customer customer;

    private List<BiochemicalTest> biochemicalTests;
    private List<ArteriesTest> arteriesTests;
    private List<MicrovesselsTest> microvesselsTests;

    private List<Comments> comments;


    public CustomerPage(Doctor doctor, Customer customer, List<BiochemicalTest> biochemicalTests, List<ArteriesTest> arteriesTests, List<MicrovesselsTest> microvesselsTests, List<Comments> comments) {
        this.doctor = doctor;
        this.customer = customer;
        this.biochemicalTests = biochemicalTests;
        this.arteriesTests = arteriesTests;
        this.microvesselsTests = microvesselsTests;
        this.comments = comments;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<BiochemicalTest> getBiochemicalTests() {
        return biochemicalTests;
    }

    public void setBiochemicalTests(List<BiochemicalTest> biochemicalTests) {
        this.biochemicalTests = biochemicalTests;
    }

    public List<ArteriesTest> getArteriesTests() {
        return arteriesTests;
    }

    public void setArteriesTests(List<ArteriesTest> arteriesTests) {
        this.arteriesTests = arteriesTests;
    }

    public List<MicrovesselsTest> getMicrovesselsTests() {
        return microvesselsTests;
    }

    public void setMicrovesselsTests(List<MicrovesselsTest> microvesselsTests) {
        this.microvesselsTests = microvesselsTests;
    }


}
