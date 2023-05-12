package com.tarashluhsko.dyplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
//    @GenericGenerator(name = "native",strategy = "native")
//    private Long id;

    @Column()
    private String firstName;
    @Column()
    private String secondName;
//    @Column
//    private String email;

//    @Column(name = "role")
//    private String role;
//    @Column
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private String password;

    @OneToMany(mappedBy = "doctorId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> patients;


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



    public List<Customer> getPatients() {
        return patients;
    }

    public void setPatients(List<Customer> patients) {
        this.patients = patients;
    }
}
