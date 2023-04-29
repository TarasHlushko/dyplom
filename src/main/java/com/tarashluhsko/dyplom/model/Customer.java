package com.tarashluhsko.dyplom.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Comments> comments;


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctorId;

//    private List<CustomerAuthority> authorities;
//
//    public List<CustomerAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<CustomerAuthority> authorities) {
//        this.authorities = authorities;
//    }

    public Doctor getDoctor() {
        return doctorId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctorId = doctor;
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
