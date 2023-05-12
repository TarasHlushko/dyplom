package com.tarashluhsko.dyplom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "arteries")
@Table(name = "arteries_test")
public class ArteriesTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(name = "pi")
    private Integer pi;
    @Column(name = "ved")
    private Integer ved;
    @Column(name = "ri")
    private Integer ri;
    @Column(name = "imc_bif")
    private Integer imc_bif;
    @Column(name = "imc")
    private Integer imc;
    @Column(name = "results")
    private Integer results;
    @Column(name = "created_dt")
    private LocalDateTime createdDt;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPi() {
        return pi;
    }

    public void setPi(Integer pi) {
        this.pi = pi;
    }

    public Integer getVed() {
        return ved;
    }

    public void setVed(Integer ved) {
        this.ved = ved;
    }

    public Integer getRi() {
        return ri;
    }

    public void setRi(Integer ri) {
        this.ri = ri;
    }

    public Integer getImc_bif() {
        return imc_bif;
    }

    public void setImc_bif(Integer imc_bif) {
        this.imc_bif = imc_bif;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(LocalDateTime createdDt) {
        this.createdDt = createdDt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
