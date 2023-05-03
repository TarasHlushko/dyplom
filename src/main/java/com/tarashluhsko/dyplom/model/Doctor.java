package com.tarashluhsko.dyplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @Column()
    private String firstName;
    @Column()
    private String secondName;
    @Column
    private String email;

    @Column(name = "role")
    private String role;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "doctorId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> patients;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    //    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
//    private Set<Roles> roles;


//    public Set<Roles> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Roles> roles) {
//        this.roles = roles;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return secondName;
    }

    public void setLastName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Customer> getPatients() {
        return patients;
    }

    public void setPatients(List<Customer> patients) {
        this.patients = patients;
    }
}
