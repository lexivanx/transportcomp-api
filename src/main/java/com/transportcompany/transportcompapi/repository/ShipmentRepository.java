package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Map;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    @Query("SELECT new map(c.companyID as companyId, c.name as companyName, SUM(s.price) as revenue) " +
            "FROM Shipment s " +
            "JOIN s.driver e " +
            "JOIN e.company c " +
            "GROUP BY c.companyID, c.name")
    List<Map<String, Object>> findCompaniesAndRevenue();

    List<Shipment> findByCustomCriteria(
            String country, String cityVillageName, String streetName, Integer streetNumber, String entrance);
}
