package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
