package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
