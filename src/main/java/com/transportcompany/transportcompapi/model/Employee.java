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

    // Constructors with no args
    public Employee() {
    }

    // Constructors with all args
    public Employee(String name, LicenseType license, boolean allowedSpecialCargo, BigDecimal salary, Company company) {
        this.name = name;
        this.license = license;
        this.allowedSpecialCargo = allowedSpecialCargo;
        this.salary = salary;
        this.company = company;
    }

    // Getters and setters for all attributes
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LicenseType getLicense() {
        return license;
    }

    public void setLicense(LicenseType license) {
        this.license = license;
    }

    public boolean isAllowedSpecialCargo() {
        return allowedSpecialCargo;
    }

    public void setAllowedSpecialCargo(boolean allowedSpecialCargo) {
        this.allowedSpecialCargo = allowedSpecialCargo;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

