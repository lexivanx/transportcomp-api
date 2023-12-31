package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

}