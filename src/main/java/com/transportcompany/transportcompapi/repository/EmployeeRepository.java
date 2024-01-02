package com.transportcompany.transportcompapi.repository;

import com.transportcompany.transportcompapi.model.Employee;
import com.transportcompany.transportcompapi.model.LicenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByLicenseAndAllowedSpecialCargoAndSalary(LicenseType license, boolean allowedSpecialCargo, BigDecimal salary);
}
