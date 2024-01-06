package com.transportcompany.transportcompapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressID;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "cityvillagename", nullable = false)
    private String cityVillageName;

    @Column(name = "streetname", nullable = false)
    private String streetName;

    @Column(name = "streetnumber", nullable = false)
    private int streetNumber;

    @Column(name = "Entrance")
    private String entrance;

    // No args constructor
    public Address() {
    }

    // All args constructor
    public Address(String country, String cityVillageName, String streetName, int streetNumber, String entrance) {
        this.country = country;
        this.cityVillageName = cityVillageName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.entrance = entrance;
    }

    // Getters and setters for all attributes
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCityVillageName() {
        return cityVillageName;
    }

    public void setCityVillageName(String cityVillageName) {
        this.cityVillageName = cityVillageName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

}

