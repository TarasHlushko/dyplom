package com.tarashluhsko.dyplom.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comments> comments;

    @Column(name = "birth_dt")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birth_dt;
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


    public LocalDate getBirth_dt() {
        return birth_dt;
    }

    public void setBirth_dt(String birth_dt) {
        System.out.println(birth_dt);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birth_dt = LocalDate.parse(birth_dt, dateTimeFormatter);
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
