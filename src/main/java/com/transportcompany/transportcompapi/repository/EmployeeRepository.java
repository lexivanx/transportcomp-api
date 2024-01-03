package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Employee;
import com.transportcompany.transportcompapi.model.LicenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByLicenseAndAllowedSpecialCargoAndSalary(LicenseType license, boolean allowedSpecialCargo, BigDecimal salary);

    // Query - all drivers with amount of shipments
    @Query("SELECT new map(e.employeeID as employeeID, e.name as name, COUNT(s) as shipmentCount) " +
            "FROM Employee e LEFT JOIN Shipment s ON e = s.driver " +
            "GROUP BY e.employeeID, e.name")
    List<Map<String, Object>> findAllDriversWithShipmentCount();

    // Query - all drivers with revenue per driver
    @Query("SELECT new map(e.employeeID as employeeID, e.name as name, SUM(s.price) as revenue) " +
            "FROM Employee e LEFT JOIN Shipment s ON e = s.driver " +
            "GROUP BY e.employeeID, e.name")
    List<Map<String, Object>> findAllDriversWithTotalRevenue();

}
