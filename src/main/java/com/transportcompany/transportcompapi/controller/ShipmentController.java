package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Shipment;
import com.transportcompany.transportcompapi.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable int id) {
        return shipmentService.getShipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Shipment> addShipment(@RequestBody Shipment shipment) {
        Shipment newShipment = shipmentService.addShipment(shipment);
        return ResponseEntity.ok(newShipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable int id, @RequestBody Shipment shipment) {
        return shipmentService.getShipmentById(id)
                .map(existingShipment -> {
                    shipment.setShipmentID(id);
                    return ResponseEntity.ok(shipmentService.updateShipment(id, shipment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable int id) {
        if (shipmentService.getShipmentById(id).isPresent()) {
            shipmentService.deleteShipment(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint for the total revenue of a company
    @GetMapping("/revenue/by-company")
    public ResponseEntity<BigDecimal> getTotalRevenueByCompany(@RequestParam int companyId) {
        BigDecimal totalRevenue = shipmentService.getTotalRevenueByCompanyId(companyId);
        return ResponseEntity.ok(totalRevenue);
    }

    // Endpoint for the total revenue of a company in a period
    @GetMapping("/revenue/by-company-in-period")
    public ResponseEntity<BigDecimal> getTotalRevenueByCompanyInPeriod(@RequestParam int companyId,
                                                                       @RequestParam Timestamp startDate,
                                                                       @RequestParam Timestamp endDate) {
        BigDecimal totalRevenue = shipmentService.getTotalRevenueByCompanyIdInPeriod(companyId, startDate, endDate);
        return ResponseEntity.ok(totalRevenue);
    }

    // Endpoint for the total amount of shipments done per company
    @GetMapping("/total-by-company")
    public ResponseEntity<Long> getTotalShipmentsByCompany(@RequestParam int companyId) {
        long totalShipments = shipmentService.getTotalShipmentsByCompanyId(companyId);
        return ResponseEntity.ok(totalShipments);
    }

    // Endpoints to filter shipments by destination address fields
    @GetMapping("/filter/destination/by-country")
    public ResponseEntity<List<Shipment>> filterShipmentsByCountry(@RequestParam String country) {
        return ResponseEntity.ok(shipmentService.filterShipmentsByCountry(country));
    }

    @GetMapping("/filter/destination/by-city")
    public ResponseEntity<List<Shipment>> filterShipmentsByCityVillageName(@RequestParam String cityVillageName) {
        return ResponseEntity.ok(shipmentService.filterShipmentsByCityVillageName(cityVillageName));
    }

    @GetMapping("/filter/destination/by-street")
    public ResponseEntity<List<Shipment>> filterShipmentsByStreetName(@RequestParam String streetName) {
        return ResponseEntity.ok(shipmentService.filterShipmentsByStreetName(streetName));
    }

    // Endpoint to sort shipments by destination address
    // It sorts by a specific field of the destination address, by default the City or Village name
    // By default it sorts in ascending order
    @GetMapping("/sort/destination")
    public ResponseEntity<List<Shipment>> sortShipmentsByDestination(
            @RequestParam(defaultValue = "CityVillageName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        List<Shipment> shipments = shipmentService.sortShipmentsBy(sortBy, sortDir);
        return ResponseEntity.ok(shipments);
    }

    // Methods for filtering Shipments by all fields
    // including by IDs of related entities
    @GetMapping("/filter/by-price")
    public ResponseEntity<List<Shipment>> getShipmentsByPrice(@RequestParam BigDecimal price) {
        return ResponseEntity.ok(shipmentService.findShipmentsByPrice(price));
    }

    @GetMapping("/filter/by-weight")
    public ResponseEntity<List<Shipment>> getShipmentsByWeight(@RequestParam BigDecimal weight) {
        return ResponseEntity.ok(shipmentService.findShipmentsByWeight(weight));
    }

    @GetMapping("/filter/by-special-cargo")
    public ResponseEntity<List<Shipment>> getShipmentsBySpecialCargo(@RequestParam boolean isSpecialCargo) {
        return ResponseEntity.ok(shipmentService.findShipmentsBySpecialCargo(isSpecialCargo));
    }

    @GetMapping("/filter/by-passenger-amount")
    public ResponseEntity<List<Shipment>> getShipmentsByPassengerAmount(@RequestParam int passengerAmount) {
        return ResponseEntity.ok(shipmentService.findShipmentsByPassengerAmount(passengerAmount));
    }

    @GetMapping("/filter/by-vehicle")
    public ResponseEntity<List<Shipment>> getShipmentsByVehicleId(@RequestParam int vehicleId) {
        return ResponseEntity.ok(shipmentService.findShipmentsByVehicleId(vehicleId));
    }

    @GetMapping("/filter/by-driver")
    public ResponseEntity<List<Shipment>> getShipmentsByDriverId(@RequestParam int driverId) {
        return ResponseEntity.ok(shipmentService.findShipmentsByDriverId(driverId));
    }

    @GetMapping("/filter/by-customer")
    public ResponseEntity<List<Shipment>> getShipmentsByCustomerId(@RequestParam int customerId) {
        return ResponseEntity.ok(shipmentService.findShipmentsByCustomerId(customerId));
    }
}