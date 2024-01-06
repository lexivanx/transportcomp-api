package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    // Custom query to get companies and their revenue
    @Query("SELECT new map(c.companyID as companyId, c.name as companyName, SUM(s.price) as revenue) " +
            "FROM Shipment s " +
            "JOIN s.driver e " +
            "JOIN e.company c " +
            "GROUP BY c.companyID, c.name")
    List<Map<String, Object>> findCompaniesAndRevenue();

    // Custom query to count shipments by company ID
    @Query("SELECT COUNT(s) FROM Shipment s WHERE s.driver.company.companyID = :companyId")
    long countByCompany(int companyId);

    // Query to get total revenue for a company
    @Query("SELECT SUM(s.price) FROM Shipment s WHERE s.driver.company.companyID = :companyId")
    BigDecimal getTotalRevenueByCompany(int companyId);

    // Query to get total revenue for a company in a specific time period
    @Query("SELECT SUM(s.price) FROM Shipment s WHERE s.driver.company.companyID = :companyId AND s.depart >= :startDate AND s.arrive <= :endDate")
    BigDecimal getTotalRevenueByCompanyInPeriod(int companyId, Timestamp startDate, Timestamp endDate);

    // Queries for filtering by destination address and other fields
    // including other fields of related entities
    List<Shipment> findByEndAddressCountry(String country);
    List<Shipment> findByEndAddressCityVillageName(String cityVillageName);
    List<Shipment> findByEndAddressStreetName(String streetName);

    List<Shipment> findByPrice(BigDecimal price);
    List<Shipment> findByWeight(BigDecimal weight);
    List<Shipment> findByIsSpecialCargo(boolean isSpecialCargo);
    List<Shipment> findByPassengerAmount(int passengerAmount);
    List<Shipment> findByVehicleVehicleID(int vehicleId);
    List<Shipment> findByDriverEmployeeID(int employeeId);
    List<Shipment> findByCustomerCustomerID(int customerId);

}
