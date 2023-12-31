package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleID;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type", nullable = false)
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "LicenseRequired", nullable = false)
    private LicenseType licenseRequired;

    @ManyToOne
    @JoinColumn(name = "Company_CompanyID", nullable = false)
    private Company company;

    // Constructors, Getters, Setters, Enum VehicleType
}

