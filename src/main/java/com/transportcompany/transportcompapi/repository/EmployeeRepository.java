package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
