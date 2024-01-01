package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyID;

    @Column(name = "Name", nullable = false)
    private String name;

    // No args constructor
    public Company() {
    }

    // All args constructor
    public Company(int companyID, String name) {
        this.companyID = companyID;
        this.name = name;
    }

    // Getters and setters for all attributes
    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

