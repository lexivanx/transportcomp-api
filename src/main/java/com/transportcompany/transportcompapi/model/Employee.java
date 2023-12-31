package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "License", nullable = false)
    private LicenseType license;

    @Column(name = "AllowedSpecialCargo")
    private boolean allowedSpecialCargo;

    @Column(name = "Salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "Company_CompanyID", nullable = false)
    private Company company;

    // Constructors, Getters, Setters, Enum LicenseType
}

