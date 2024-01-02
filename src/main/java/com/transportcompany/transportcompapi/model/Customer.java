package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

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

    @Column(name = "BillToPay", nullable = false)
    private BigDecimal billToPay;

    @Column(name = "AmountPaid", nullable = false)
    private BigDecimal amountPaid;

    // Constructor with no args
    public Customer() {
    }

    // Constructor /w all args
    public Customer(int customerID, String name, boolean isAllPaid) {
        this.customerID = customerID;
        this.name = name;
        this.isAllPaid = isAllPaid;
    }

    // Getters and setters for all attributes
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAllPaid() {
        return isAllPaid;
    }

    public void setIsAllPaid(boolean isAllPaid) {
        this.isAllPaid = isAllPaid;
    }

    public BigDecimal getBillToPay() {
        return billToPay;
    }

    public void setBillToPay(BigDecimal billToPay) {
        this.billToPay = billToPay;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}
