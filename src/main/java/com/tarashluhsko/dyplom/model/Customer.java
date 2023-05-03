package com.tarashluhsko.dyplom.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secondName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comments> comments;

    @Column(name = "birth_dt")
    private LocalDateTime birthDate;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctorId;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<BiochemicalTest> biochemicalTests;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<ArteriesTest> arteriesTests;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<MicrovesselsTest> microvesselsTests;


    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
