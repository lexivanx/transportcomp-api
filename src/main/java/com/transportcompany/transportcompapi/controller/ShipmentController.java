package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Shipment;
import com.transportcompany.transportcompapi.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
        if (updatedShipment != null) {
            return ResponseEntity.ok(updatedShipment);
        } else {
            return ResponseEntity.notFound().build();
        }
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

    // Endpoint to filter shipments by destination address
    @GetMapping("/filter/destination")
    public ResponseEntity<List<Shipment>> filterShipmentsByDestination(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String cityVillageName,
            @RequestParam(required = false) String streetName,
            @RequestParam(required = false) Integer streetNumber,
            @RequestParam(required = false) String entrance) {

        List<Shipment> shipments = shipmentService.filterShipmentsByDestination(country, cityVillageName, streetName, streetNumber, entrance);
        return ResponseEntity.ok(shipments);
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

    // TODO Additional endpoints as required
}