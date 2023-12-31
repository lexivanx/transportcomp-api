package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "IsAllPaid")
    private boolean isAllPaid;

    // Constructors, Getters, Setters
}
