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
    @Column(name = "licenserequired", nullable = false)
    private LicenseType licenseRequired;

    @ManyToOne
    @JoinColumn(name = "Company_CompanyID", nullable = false)
    private Company company;

    // Constructors with no args
    public Vehicle() {
    }

    // Constructors with args
    public Vehicle(VehicleType type, LicenseType licenseRequired, Company company) {
        this.type = type;
        this.licenseRequired = licenseRequired;
        this.company = company;
    }

    // Getters and setters for all attributes
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public LicenseType getLicenseRequired() {
        return licenseRequired;
    }

    public void setLicenseRequired(LicenseType licenseRequired) {
        this.licenseRequired = licenseRequired;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

