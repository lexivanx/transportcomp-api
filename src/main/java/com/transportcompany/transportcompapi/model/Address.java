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

    @Column(name = "CityVillageName", nullable = false)
    private String cityVillageName;

    @Column(name = "StreetName", nullable = false)
    private String streetName;

    @Column(name = "StreetNumber", nullable = false)
    private int streetNumber;

    @Column(name = "Entrance")
    private String entrance;

    // Constructors, Getters, Setters
}

