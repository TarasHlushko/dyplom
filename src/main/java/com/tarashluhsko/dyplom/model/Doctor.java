package com.tarashluhsko.dyplom.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

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
    @Column
    private String password;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Customer> patients;



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
