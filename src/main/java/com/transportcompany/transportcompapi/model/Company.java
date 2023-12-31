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

    // Constructors, Getters, Setters
}

