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

    // Constructor with no args
    public Shipment() {
    }

    // Constructor with all args
    public Shipment(int shipmentID, Timestamp depart, Timestamp arrive, String description, BigDecimal price, BigDecimal weight, int passengerAmount, boolean isSpecialCargo, Customer customer, Employee driver, Vehicle vehicle, Address startAddress, Address endAddress) {
        this.shipmentID = shipmentID;
        this.depart = depart;
        this.arrive = arrive;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.passengerAmount = passengerAmount;
        this.isSpecialCargo = isSpecialCargo;
        this.customer = customer;
        this.driver = driver;
        this.vehicle = vehicle;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    // Getters and setters for all attributes

    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public Timestamp getDepart() {
        return depart;
    }

    public void setDepart(Timestamp depart) {
        this.depart = depart;
    }

    public Timestamp getArrive() {
        return arrive;
    }

    public void setArrive(Timestamp arrive) {
        this.arrive = arrive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public int getPassengerAmount() {
        return passengerAmount;
    }

    public void setPassengerAmount(int passengerAmount) {
        this.passengerAmount = passengerAmount;
    }

    public boolean isSpecialCargo() {
        return isSpecialCargo;
    }

    public void setSpecialCargo(boolean specialCargo) {
        isSpecialCargo = specialCargo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }

    public Address getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(Address endAddress) {
        this.endAddress = endAddress;
    }
}

