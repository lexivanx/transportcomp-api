package com.transportcompany.transportcompapi.controller;

import com.transportcompany.transportcompapi.model.Address;
import com.transportcompany.transportcompapi.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Address addAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address address) {
        return addressService.getAddressById(id)
                .map(existingAddress -> {
                    address.setAddressID(id);
                    return ResponseEntity.ok(addressService.saveAddress(address));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
        if (addressService.getAddressById(id).isPresent()) {
            addressService.deleteAddress(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
