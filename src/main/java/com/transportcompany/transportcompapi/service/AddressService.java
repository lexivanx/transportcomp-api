package com.transportcompany.transportcompapi.service;

import com.transportcompany.transportcompapi.model.Address;
import com.transportcompany.transportcompapi.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(int id) {
        return addressRepository.findById(id);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void updateAddress(int id, Address updatedAddress) {
        if (addressRepository.existsById(id)) {
            updatedAddress.setAddressID(id);
            addressRepository.save(updatedAddress);
        }
        // TODO else, handle the case where the address doesn't exist
    }

    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }

}

