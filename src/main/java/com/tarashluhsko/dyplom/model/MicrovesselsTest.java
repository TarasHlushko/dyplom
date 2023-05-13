package com.tarashluhsko.dyplom.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "microvessel")
@Table(name = "microvessels_test")
public class MicrovesselsTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "bmi")
    private Integer bmi;

    @Column(name = "blood_flow")
    private Integer bloodFlow;

    @Column(name = "waist_hips")
    private Integer waistHips;

    @Column(name = "recovery_speed")
    private Integer recoverySpeed;

    @Column(name = "platelets")
    private Integer platelets;

    @Column(name = "results")
    private Integer results;


    @Column(name = "created_dt")
    private LocalDateTime created;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBmi() {
        return bmi;
    }

    public void setBmi(Integer bmi) {
        this.bmi = bmi;
    }

    public Integer getBloodFlow() {
        return bloodFlow;
    }

    public void setBloodFlow(Integer bloodFlow) {
        this.bloodFlow = bloodFlow;
    }

    public Integer getWaistHips() {
        return waistHips;
    }

    public void setWaistHips(Integer waistHips) {
        this.waistHips = waistHips;
    }

    public Integer getRecoverySpeed() {
        return recoverySpeed;
    }

    public void setRecoverySpeed(Integer recoverySpeed) {
        this.recoverySpeed = recoverySpeed;
    }

    public Integer getPlatelets() {
        return platelets;
    }

    public void setPlatelets(Integer platelets) {
        this.platelets = platelets;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
