package com.transportcompany.transportcompapi.service;

import com.transportcompany.transportcompapi.model.Customer;
import com.transportcompany.transportcompapi.model.Shipment;
import com.transportcompany.transportcompapi.repository.CustomerRepository;
import com.transportcompany.transportcompapi.repository.ShipmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final CustomerRepository customerRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, CustomerRepository customerRepository) {
        this.shipmentRepository = shipmentRepository;
        this.customerRepository = customerRepository;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getShipmentById(int id) {
        return shipmentRepository.findById(id);
    }

    public void deleteShipment(int id) {
        shipmentRepository.deleteById(id);
    }

    // Method to update the BillToPay field of a customer
    // The old price is subtracted from the BillToPay field and the new price is added
    private void updateCustomerBillToPay(Customer customer, BigDecimal price, BigDecimal oldPrice) {
        BigDecimal newBillToPay = customer.getBillToPay().add(price).subtract(oldPrice);
        customer.setBillToPay(newBillToPay);
        customerRepository.save(customer);
    }

    // Method to add a shipment and charge its price to the customer
    public Shipment addShipment(Shipment shipment) {
        updateCustomerBillToPay(shipment.getCustomer(), shipment.getPrice(), BigDecimal.ZERO);
        return shipmentRepository.save(shipment);
    }

    // Method to update a shipment and charge its new price to the customer
    public Shipment updateShipment(int shipmentId, Shipment updatedShipmentData) {
        Optional<Shipment> existingShipmentOpt = shipmentRepository.findById(shipmentId);
        if (existingShipmentOpt.isPresent()) {
            Shipment existingShipment = existingShipmentOpt.get();

            // Calculate the price difference
            BigDecimal priceDifference = updatedShipmentData.getPrice().subtract(existingShipment.getPrice());

            // Update the customer's bill
            updateCustomerBillToPay(existingShipment.getCustomer(), priceDifference, existingShipment.getPrice());

            // Update fields of the existing shipment
            existingShipment.setDepart(updatedShipmentData.getDepart());
            existingShipment.setArrive(updatedShipmentData.getArrive());
            existingShipment.setDescription(updatedShipmentData.getDescription());
            existingShipment.setPrice(updatedShipmentData.getPrice());
            existingShipment.setWeight(updatedShipmentData.getWeight());
            existingShipment.setPassengerAmount(updatedShipmentData.getPassengerAmount());
            existingShipment.setSpecialCargo(updatedShipmentData.isSpecialCargo());
            existingShipment.setCustomer(updatedShipmentData.getCustomer());
            existingShipment.setDriver(updatedShipmentData.getDriver());
            existingShipment.setVehicle(updatedShipmentData.getVehicle());
            existingShipment.setStartAddress(updatedShipmentData.getStartAddress());
            existingShipment.setEndAddress(updatedShipmentData.getEndAddress());

            // Save the updated shipment
            return shipmentRepository.save(existingShipment);
        }
        return null;
    }

    // Method to calculate the total revenue of a company
    public BigDecimal getTotalRevenueByCompanyId(int companyId) {
        return shipmentRepository.getTotalRevenueByCompany(companyId);
    }

    // Method to calculate the total revenue of a company in a period
    public BigDecimal getTotalRevenueByCompanyIdInPeriod(int companyId, Timestamp startDate, Timestamp endDate) {
        return shipmentRepository.getTotalRevenueByCompanyInPeriod(companyId, startDate, endDate);
    }

    // Methods to filter shipments by end address fields
    public List<Shipment> filterShipmentsByCountry(String country) {
        return shipmentRepository.findByEndAddressCountry(country);
    }

    public List<Shipment> filterShipmentsByCityVillageName(String cityVillageName) {
        return shipmentRepository.findByEndAddressCityVillageName(cityVillageName);
    }

    public List<Shipment> filterShipmentsByStreetName(String streetName) {
        return shipmentRepository.findByEndAddressStreetName(streetName);
    }

    // Method to sort shipments by criteria, City or Village name
    public List<Shipment> sortShipmentsBy(String sortBy, String sortDir) {
        Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC;
        // Determine which Address field is chosen
        String sortProperty = switch (sortBy) {
            case "Country" -> "endAddress.country";
            case "CityVillageName" -> "endAddress.cityVillageName";
            case "StreetName" -> "endAddress.streetName";
            case "StreetNumber" -> "endAddress.streetNumber";
            case "Entrance" -> "endAddress.entrance";
            default -> sortBy; // fields directly on the Shipment entity
        };
        return shipmentRepository.findAll(Sort.by(direction, sortProperty));
    }

    // Methods to filter by other shipment fields
    public List<Shipment> findShipmentsByPrice(BigDecimal price) {
        return shipmentRepository.findByPrice(price);
    }

    public List<Shipment> findShipmentsByWeight(BigDecimal weight) {
        return shipmentRepository.findByWeight(weight);
    }

    public List<Shipment> findShipmentsBySpecialCargo(boolean isSpecialCargo) {
        return shipmentRepository.findByIsSpecialCargo(isSpecialCargo);
    }

    public List<Shipment> findShipmentsByPassengerAmount(int passengerAmount) {
        return shipmentRepository.findByPassengerAmount(passengerAmount);
    }

    public List<Shipment> findShipmentsByVehicleId(int vehicleId) {
        return shipmentRepository.findByVehicleVehicleID(vehicleId);
    }

    public List<Shipment> findShipmentsByDriverId(int employeeId) {
        return shipmentRepository.findByDriverEmployeeID(employeeId);
    }

    public List<Shipment> findShipmentsByCustomerId(int customerId) {
        return shipmentRepository.findByCustomerCustomerID(customerId);
    }

    // Method to calculate the total number of shipments of a company
    public long getTotalShipmentsByCompanyId(int companyId) {
        return shipmentRepository.countByCompany(companyId);
    }
}

