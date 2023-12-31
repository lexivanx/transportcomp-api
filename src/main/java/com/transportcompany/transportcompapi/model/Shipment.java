package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentID;

    @Column(name = "Depart", nullable = false)
    private Timestamp depart;

    @Column(name = "Arrive")
    private Timestamp arrive;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Weight")
    private BigDecimal weight;

    @Column(name = "PassengerAmount")
    private int passengerAmount;

    @Column(name = "IsSpecialCargo")
    private boolean isSpecialCargo;

    @ManyToOne
    @JoinColumn(name = "Customer_CustomerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "Employee_EmployeeID", nullable = false)
    private Employee driver;

    @ManyToOne
    @JoinColumn(name = "Vehicle_VehicleID", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "StartAddressID", nullable = false)
    private Address startAddress;

    @ManyToOne
    @JoinColumn(name = "EndAddressID", nullable = false)
    private Address endAddress;

    // Constructors, Getters, Setters
}

