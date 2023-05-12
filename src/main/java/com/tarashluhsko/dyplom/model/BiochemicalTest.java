package com.tarashluhsko.dyplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "biochemical")
@Table(name = "biochemical_test")
public class BiochemicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "BMI")
    private Integer bmi;
    @Column(name = "waist_hips")
    private Integer waistHips;

    @Column(name = "GTT")
    private Integer gtt;
    @Column(name = "VLDL")
    private Integer vldl;
    @Column(name = "urea")
    private Integer urea;
    @Column(name = "alt")
    private Integer alt;
    @Column(name = "alkaine")
    private Integer alkaine;

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

    public Integer getWaistHips() {
        return waistHips;
    }

    public void setWaistHips(Integer waistHips) {
        this.waistHips = waistHips;
    }

    public Integer getGtt() {
        return gtt;
    }

    public void setGtt(Integer gtt) {
        this.gtt = gtt;
    }

    public Integer getVldl() {
        return vldl;
    }

    public void setVldl(Integer vldl) {
        this.vldl = vldl;
    }

    public Integer getUrea() {
        return urea;
    }

    public void setUrea(Integer urea) {
        this.urea = urea;
    }

    public Integer getAlt() {
        return alt;
    }

    public void setAlt(Integer alt) {
        this.alt = alt;
    }

    public Integer getAlkaine() {
        return alkaine;
    }

    public void setAlkaine(Integer alkaine) {
        this.alkaine = alkaine;
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

    @Override
    public String toString() {
        return "BiochemicalTest{" +
                "id=" + id +
                ", bmi=" + bmi +
                ", waistHips=" + waistHips +
                ", gtt=" + gtt +
                ", vldl=" + vldl +
                ", urea=" + urea +
                ", alt=" + alt +
                ", alkaine=" + alkaine +
                ", results=" + results +
                ", created=" + created +
                ", customer=" + customer +
                '}';
    }
}
